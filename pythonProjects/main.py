import math
import pandas as pd
import numpy as np

date_static = '2021-06-06'
draw_gap = 0.3
percentage_gap = 5
percentage_priority_coefficient = 0.8
date_priority_coefficient = 1.4
match_count_limit = 9
goal_normalize_coefficient = 100
normalized_goal_gap = 0.5
normalized_draw_gap = 20
money_limit = 128 * 2
learning_mode = 0
next_week_mode = 1
side_coefficient = 0.7
debug_mode = 0
goal_diff_normalizer = 0.33


def predict_score(df):
    total_home_contribution = df['contribution_home'].sum()
    total_home_priority = df['priority_home'].sum()
    total_away_contribution = df['contribution_away'].sum()
    total_away_priority = df['priority_away'].sum()
    if debug_mode == 1:
        print("predict: ", total_home_contribution/total_home_priority, "  -  ", total_away_contribution/total_away_priority)
    return total_home_contribution/total_home_priority, total_away_contribution/total_away_priority


def create_winner_column(row):
    if row['homeMatchScore'] > row['awayMatchScore']:
        return '1'
    elif row['homeMatchScore'] < row['awayMatchScore']:
        return '2'
    else:
        return '0'


def calculate_percentages_of_predicted_scores(row):
    min_1, min_2, max_1, max_2 = row['normalized_predicted_goal_home_min'], row['normalized_predicted_goal_away_min'],\
                                 row['normalized_predicted_goal_home_max'], row['normalized_predicted_goal_away_max']
    r_1, r_0, r_2 = 0, 0, 0
    for i in range(min_1, max_1 + 1):
        for j in range(min_2, max_2 + 1):
            if i - j > normalized_draw_gap:
                r_1 += 1
            elif i - j < -normalized_draw_gap:
                r_2 += 1
            else:
                r_0 += 1
    return r_1, r_0, r_2


def calculate_match_result_from_predicted_values(row):
    if row['total_predicted_home_score'] - row['total_predicted_away_score'] > draw_gap:
        return 'home'
    elif row['total_predicted_home_score'] - row['total_predicted_away_score'] < -draw_gap:
        return 'away'
    else:
        return 'draw'


def calculate_success(row):
    if row['real_winner'] in row['toto_results']:
        return 1
    else:
        return 0


def calculate_priority(target_percentage, row, target):
    starter_coefficient = 1000
    if target == 1:
        percentage_distance = abs(row['handicapPercentage1'] - target_percentage)
    else:
        percentage_distance = abs(row['handicapPercentage2'] - target_percentage)
    percentage_diff = math.floor(percentage_distance / percentage_gap)
    return starter_coefficient * date_priority_coefficient**row['order'] * percentage_priority_coefficient**percentage_diff * row["side_coefficient"]


def get_spor_toto_week(week_number):
    spor_toto_url = ''
    if next_week_mode:
        spor_toto_url = f'http://localhost:8080/currentSporTotoList/{week_number}'
    else:
        spor_toto_url = f'http://localhost:8080/sporTotoWeek/{week_number}'
    df = pd.read_json(spor_toto_url)
    home_tuple_list = [predict_match_result_for_home(row) for index, row in df.iterrows()]
    away_tuple_list = [predict_match_result_for_away(row) for index, row in df.iterrows()]
    df['home_predicted_home_score'], df['home_predicted_away_score'] = [x[0] for x in home_tuple_list], [x[1] for x in home_tuple_list]
    df['away_predicted_home_score'], df['away_predicted_away_score'] = [x[0] for x in away_tuple_list], [x[1] for x in away_tuple_list]
    df['total_predicted_home_score'] = df.apply(lambda row: (row['home_predicted_home_score'] + row['away_predicted_home_score']) / 2, axis=1)
    df['total_predicted_away_score'] = df.apply(lambda row: (row['home_predicted_away_score'] + row['away_predicted_away_score']) / 2, axis=1)
    df['normalized_predicted_goal_home_min'] = df.apply(lambda row: round(row['total_predicted_home_score'] * goal_normalize_coefficient *(1-normalized_goal_gap)), axis=1)
    df['normalized_predicted_goal_home_max'] = df.apply(
        lambda row: round(row['total_predicted_home_score'] * goal_normalize_coefficient * (1 + normalized_goal_gap)),
        axis=1)
    df['normalized_predicted_goal_away_min'] = df.apply(
        lambda row: round(row['total_predicted_away_score'] * goal_normalize_coefficient * (1 - normalized_goal_gap)),
        axis=1)
    df['normalized_predicted_goal_away_max'] = df.apply(
        lambda row: round(row['total_predicted_away_score'] * goal_normalize_coefficient * (1 + normalized_goal_gap)),
        axis=1)
    predicted_percentage_match_score_tuple = [calculate_percentages_of_predicted_scores(row) for index, row in df.iterrows()]
    df['pps1'] = [x[0] * 100/(x[0] + x[1] + x[2]) for x in predicted_percentage_match_score_tuple]
    df['pps0'] = [x[1] * 100/(x[0] + x[1] + x[2]) for x in predicted_percentage_match_score_tuple]
    df['pps2'] = [x[2] * 100/(x[0] + x[1] + x[2]) for x in predicted_percentage_match_score_tuple]
    df['predicted_winner'] = df.apply(lambda row: calculate_match_result_from_predicted_values(row), axis=1)
    df['date'] = df['date'].dt.tz_localize(None)
    df.drop(['id', 'weekNumber', 'date', 'home_predicted_home_score', 'home_predicted_away_score', 'away_predicted_home_score', 'away_predicted_away_score',
        'normalized_predicted_goal_home_min', 'normalized_predicted_goal_home_max', 'normalized_predicted_goal_away_min', 'normalized_predicted_goal_away_max'], inplace=True, axis=1)
    combinations = find_best_combination()
    if next_week_mode:
        with pd.ExcelWriter("output/spor_toto_next_week_predicts.xlsx") as writer:
            for c in combinations:
                max_chosen_matches, max_percentage_of_toto = find_best_sport_toto(df, [c])
                max_chosen_matches = sorted(max_chosen_matches, key=lambda l: (l[2]))
                new_column_list = []
                for index, row in df.iterrows():
                    temp_arr = [[row['pps1'], 1], [row['pps0'], 0], [row['pps2'], 2]]
                    temp_arr.sort(reverse=True)
                    s = ''
                    if max_chosen_matches[index][1] == 1:
                        s = f'{temp_arr[0][1]}'
                    elif max_chosen_matches[index][1] == 2:
                        s = f'{temp_arr[0][1]} - {temp_arr[1][1]}'
                    else:
                        s = f'{temp_arr[0][1]} - {temp_arr[1][1]} - {temp_arr[2][1]}'
                    new_column_list.append(s)
                df['toto_results'] = new_column_list
                df.to_excel(writer, sheet_name=f'cost_{c[0]}_i_{c[1]}_j_{c[2]}', index=False)
        return 0, len(df.index)
    else:
        max_chosen_matches, max_percentage_of_toto = find_best_sport_toto(df, combinations)
        max_chosen_matches = sorted(max_chosen_matches, key=lambda l: (l[2]))

        new_column_list = []
        for index, row in df.iterrows():
            temp_arr = [[row['pps1'], 1], [row['pps0'], 0], [row['pps2'], 2]]
            temp_arr.sort(reverse=True)
            s = ''
            if max_chosen_matches[index][1] == 1:
                s = f'{temp_arr[0][1]}'
            elif max_chosen_matches[index][1] == 2:
                s = f'{temp_arr[0][1]} - {temp_arr[1][1]}'
            else:
                s = f'{temp_arr[0][1]} - {temp_arr[1][1]} - {temp_arr[2][1]}'
            new_column_list.append(s)
        df['toto_results'] = new_column_list
        df['real_winner'] = df.apply(lambda row: create_winner_column(row), axis=1)
        df['success'] = df.apply(lambda row: calculate_success(row), axis=1)
        if learning_mode != 1:
            df.to_excel('output/spor_toto_predicts.xlsx')
        if learning_mode != 1:
            print('Total succes:', df['success'].sum(), len(df.index))
        return df['success'].sum(), len(df.index)


def find_best_sport_toto(df, combinations):
    max_percentage_of_toto = 0
    max_chosen_matches = []
    for c in combinations:
        chosen_matches = calculate_triple_double_arr(df, c[1], c[2])
        temp_percentage_of_toto = 1
        for i in range(len(chosen_matches)):
            temp_percentage_of_toto *= chosen_matches[i][0] / 100
        if learning_mode != 1:
            print(f'2**{c[1]}, 3**{c[2]}, {temp_percentage_of_toto}')
        if temp_percentage_of_toto > max_percentage_of_toto:
            max_percentage_of_toto = temp_percentage_of_toto
            max_chosen_matches = chosen_matches.copy()
    return max_chosen_matches, max_percentage_of_toto


def calculate_triple_double_arr(df, n, m):
    efficiency_arr = []
    result_arr = []
    for index, row in df.iterrows():
        temp_arr = [row['pps1'], row['pps0'], row['pps2']]
        temp_arr.sort(reverse=True)
        double_util_temp = ((temp_arr[0]+temp_arr[1])/temp_arr[0])/2
        triple_util_temp = (100/temp_arr[0])/3
        best_util_temp = max(double_util_temp, triple_util_temp)
        efficiency_arr.append([best_util_temp, temp_arr[0], double_util_temp, triple_util_temp, index])
    efficiency_arr.sort(reverse=True)
    for i in range(15):
        if n == 0:
            efficiency_arr = sorted(efficiency_arr, key=lambda l: (l[3]), reverse=True)
            result_arr.extend([[efficiency_arr[a][3] * 3 * efficiency_arr[a][1], 3, efficiency_arr[a][4]] for a in range(m)])
            efficiency_arr = efficiency_arr[m:]
            efficiency_arr = sorted(efficiency_arr, key=lambda l: (l[1]), reverse=True)
            result_arr.extend([[efficiency_arr[a][1], 1, efficiency_arr[a][4]] for a in range(len(efficiency_arr))])
            return result_arr
        if m == 0:
            efficiency_arr = sorted(efficiency_arr, key=lambda l: (l[2]), reverse=True)
            result_arr.extend([[efficiency_arr[a][2] * 2 * efficiency_arr[a][1], 2, efficiency_arr[a][4]] for a in range(n)])
            efficiency_arr = efficiency_arr[n:]
            efficiency_arr = sorted(efficiency_arr, key=lambda l: (l[1]), reverse=True)
            result_arr.extend([[efficiency_arr[a][1], 1, efficiency_arr[a][4]] for a in range(len(efficiency_arr))])
            return result_arr
        if efficiency_arr[0][0] == efficiency_arr[0][2]:
            result_arr.append([efficiency_arr[0][2] * 2 * efficiency_arr[0][1], 2, efficiency_arr[0][4]])
            n -= 1
        else:
            result_arr.append([efficiency_arr[0][3] * 3 * efficiency_arr[0][1], 3, efficiency_arr[0][4]])
            m -=1
        efficiency_arr = efficiency_arr[1:]

    return result_arr


def normalize_goal_difference(row, is_home):
    x = 0
    if is_home:
        if row['homeMatchScore'] > row['awayMatchScore']:
            n = row['homeMatchScore'] - row['awayMatchScore'] - 1
            if n > round(1/goal_diff_normalizer):
                x = n - round(1/goal_diff_normalizer)
                n = round(1/goal_diff_normalizer)
            return row['homeMatchScore'] - n * (n+1) * goal_diff_normalizer / 2 - x
        else:
            return row['homeMatchScore']
    else:
        if row['homeMatchScore'] < row['awayMatchScore']:
            n = row['awayMatchScore'] - row['homeMatchScore'] - 1
            if n > round(1/goal_diff_normalizer):
                x = n - round(1/goal_diff_normalizer)
                n = round(1/goal_diff_normalizer)
            return row['awayMatchScore'] - n * (n + 1) * goal_diff_normalizer / 2 - x
        else:
            return row['awayMatchScore']


def get_home_df(teamId, date, target_home_percentage, target_away_percentage):
    date = date.replace('/', '-')
    match_detail_url_home = f'http://localhost:8080/matchDetailHome/{teamId}/2021-01-01/{date}'
    match_detail_url_away = f'http://localhost:8080/matchDetailAway/{teamId}/2021-01-01/{date}'
    df_temp_home = pd.read_json(match_detail_url_home)
    df_temp_away = pd.read_json(match_detail_url_away)
    df_temp_home = df_temp_home.tail(match_count_limit)
    df_temp_away = df_temp_away.tail(match_count_limit)
    df_temp_home["side_coefficient"] = 1
    df_temp_away["side_coefficient"] = side_coefficient
    df_temp_away.rename(columns={'homeMatchScore': 'awayMatchScore', 'awayMatchScore': 'homeMatchScore'}, inplace=True)
    df_temp_away.rename(columns={'handicapPercentage1': 'handicapPercentage2', 'handicapPercentage2': 'handicapPercentage1'}, inplace=True)
    df_temp = pd.concat([df_temp_home, df_temp_away])
    df_temp.sort_values(by=['date'], inplace=True)
    df_temp['winner'] = df_temp.apply(lambda row: create_winner_column(row), axis=1)
    df_temp["order"] = [i for i in range(1, 1 + df_temp.shape[0])]
    df_temp['priority_home'] = df_temp.apply(lambda row: calculate_priority(target_home_percentage, row, 1), axis=1)
    df_temp['normalizedHomeMatchScore'] = df_temp.apply(lambda row: normalize_goal_difference(row, True), axis=1)
    df_temp['contribution_home'] = df_temp.apply(lambda row: row['normalizedHomeMatchScore'] * row['priority_home'], axis=1)
    df_temp['priority_away'] = df_temp.apply(lambda row: calculate_priority(target_away_percentage, row, 2), axis=1)
    df_temp['normalizedAwayMatchScore'] = df_temp.apply(lambda row: normalize_goal_difference(row, False), axis=1)
    df_temp['contribution_away'] = df_temp.apply(lambda row: row['normalizedAwayMatchScore'] * row['priority_away'], axis=1)
    df_temp.drop(['id', 'teamName'], inplace=True, axis=1)
    if debug_mode == 1:
        print()
        print()
        print("Home Team Id: ", teamId)
        for index, row in df_temp.iterrows():
            print(row['homeMatchScore'], "-", row['awayMatchScore'], "- side: ", row['side_coefficient'])
    return df_temp


def get_away_df(teamId, date, target_home_percentage, target_away_percentage):
    date = date.replace('/', '-')
    match_detail_url_home = f'http://localhost:8080/matchDetailHome/{teamId}/2021-01-01/{date}'
    match_detail_url_away = f'http://localhost:8080/matchDetailAway/{teamId}/2021-01-01/{date}'
    df_temp_home = pd.read_json(match_detail_url_home)
    df_temp_away = pd.read_json(match_detail_url_away)
    df_temp_home = df_temp_home.tail(match_count_limit)
    df_temp_away = df_temp_away.tail(match_count_limit)
    df_temp_home["side_coefficient"] = side_coefficient
    df_temp_home.rename(columns = {'homeMatchScore':'awayMatchScore', 'awayMatchScore':'homeMatchScore'}, inplace = True)
    df_temp_away["side_coefficient"] = 1
    df_temp = pd.concat([df_temp_home, df_temp_away])
    df_temp.sort_values(by=['date'], inplace=True)
    df_temp['winner'] = df_temp.apply(lambda row: create_winner_column(row), axis=1)
    df_temp["order"] = [i for i in range(1, 1 + df_temp.shape[0])]
    df_temp['priority_home'] = df_temp.apply(lambda row: calculate_priority(target_home_percentage, row, 1), axis=1)
    df_temp['normalizedHomeMatchScore'] = df_temp.apply(lambda row: normalize_goal_difference(row, True), axis=1)
    df_temp['contribution_home'] = df_temp.apply(lambda row: row['normalizedHomeMatchScore'] * row['priority_home'], axis=1)
    df_temp['priority_away'] = df_temp.apply(lambda row: calculate_priority(target_away_percentage, row, 2), axis=1)
    df_temp['normalizedAwayMatchScore'] = df_temp.apply(lambda row: normalize_goal_difference(row, False), axis=1)
    df_temp['contribution_away'] = df_temp.apply(lambda row: row['normalizedAwayMatchScore'] * row['priority_away'], axis=1)

    df_temp.drop(['id', 'teamName'], inplace=True, axis=1)
    if debug_mode == 1:
        print()
        print()
        print("Away Team Id: ", teamId)
        for index, row in df_temp.iterrows():
            print(row['homeMatchScore'], "-", row['awayMatchScore'], "- side: ", row['side_coefficient'])
    return df_temp


def predict_match_result_for_home(row):
    row['date'] = pd.to_datetime(row['date']).strftime('%Y/%m/%d')
    home_team_df = get_home_df(row['homeId'], row['date'], row['handicapPercentage1'], row['handicapPercentage2'])
    return predict_score(home_team_df)


def predict_match_result_for_away(row):
    row['date'] = pd.to_datetime(row['date']).strftime('%Y/%m/%d')
    away_team_df = get_away_df(row['awayId'], row['date'], row['handicapPercentage1'], row['handicapPercentage2'])
    return predict_score(away_team_df)


def deneme():
    row = pd.DataFrame(columns=['date', 'homeId', ])
    match_detail_url = f'http://localhost:8080/matchDetailHome/30/2021-01-01/2022-08-27'
    df_temp = pd.read_json(match_detail_url)


def find_best_combination():
    combination_list = []
    for i in range(15):
        for j in range(15):
            if 2**i * 3**j > money_limit:
                break
            combination_list.append([2**i * 3**j, i, j])
    combination_list.sort(reverse=True)
    for i in range(len(combination_list)):
        if combination_list[i][0] <= 0.75 * combination_list[0][0]:
            return combination_list[0:i]

    return 0


def predict_spor_toto():
    pd.set_option('display.max_columns', None)
    #df_md = create_data_frame(URL)
    #print(predict_score(df_md))
    r = 0
    c = 0
    for i in range(141, 154):
        t, mc = get_spor_toto_week(i)
        r += t
        c += mc
        if learning_mode != 1:
            print(i, t)
    print(f'avg: {r / c}')
    """r = 0
        for i in range(130, 152):
        print(f'Week Number: {i}')
        get_spor_toto_week(i)"""

    """df_md['date'] = df_md['date'].dt.tz_localize(None)
    result_df = predict_future(df_md, ['homeMatchScore', 'awayMatchScore'], ['handicapPercentage1', 'handicapPercentageX', 'handicapPercentage2'])
    winner_df = predict_future(df_md, ['winner'], ['handicapPercentage1', 'handicapPercentageX', 'handicapPercentage2', 'homeMatchScore', 'awayMatchScore'])
    """

    return r / c


if __name__ == '__main__':
    if next_week_mode == 1:
        get_spor_toto_week(154)
    else:
        if learning_mode != 1:
            predict_spor_toto()
        else:
            learning_arr = []
            for i in range(9, 6, -1):
                print('i:' , i)
                match_count_limit = i
                for j in range(3, 6):
                    print('j:' , j)
                    percentage_gap = j
                    for k in np.arange (0.7, 0.95, 0.1):
                        print('k:' , k)
                        percentage_priority_coefficient = k
                        for l in np.arange(1.4, 1.65, 0.1):
                            date_priority_coefficient = l
                            for m in range(90, 110, 10):
                                goal_normalize_coefficient = m
                                for n in np.arange(0.5, 0.65, 0.1):
                                    normalized_goal_gap = n
                                    for o in range(20, 30, 5):
                                        normalized_draw_gap = o
                                        for p in np.arange(0.3, 0.75, 0.1):
                                            side_coefficient = p
                                            ret = predict_spor_toto()
                                            learning_arr.append([ret, i, j, k, l, m, n, o, p])
            #learning_arr.sort()
            #learning_arr=learning_arr[::-1]

            learning_arr = sorted(learning_arr, key=lambda l: (l[0]), reverse=True)

            for i in range(10):
                print(learning_arr[i])
            print()
            for i in range(10):
                print(learning_arr[len(learning_arr) - 1 -i])
            
        


"""
percentage_gap = 3 + j (3, 6)
percentage_priority_coefficient = 0.7 + k (0.5, 0.8)
date_priority_coefficient = 1.4 + l (1.2, 1.6)
match_count_limit = 15 + i (15, 7)
goal_normalize_coefficient = 100 + m (80, 120)
normalized_goal_gap = 0.6 + n (0.4, 0.7)
normalized_draw_gap = 25 + o (15, 30)
"""