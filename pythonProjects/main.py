import math
import pandas as pd

URL = "http://localhost:8080/matchDetailAway/1/2021-01-01/2022-08-01"
date_static = '2022-01-01'


def create_data_frame(url):
    df_temp = pd.read_json(URL)
    df_temp['winner'] = df_temp.apply(lambda row: create_winner_column(row), axis=1)
    df_temp["order"] = [i for i in range(1, 1 + df_temp.shape[0])]
    df_temp['priority'] = df_temp.apply(lambda row: calculate_priority(54.286045, row), axis=1)
    df_temp['contribution'] = df_temp.apply(lambda row: row['awayMatchScore'] * row['priority'], axis=1)
    print(df_temp)
    df_temp.drop(['id', 'teamName'], inplace=True, axis=1)
    return df_temp


def predict_score(df):
    total_home_contribution = df['contribution_home'].sum()
    total_home_priority = df['priority_home'].sum()
    total_away_contribution = df['contribution_away'].sum()
    total_away_priority = df['priority_away'].sum()
    return total_home_contribution/total_home_priority, total_away_contribution/total_away_priority


def create_winner_column(row):
    if row['homeMatchScore'] > row['awayMatchScore']:
        return 'home'
    elif row['homeMatchScore'] < row['awayMatchScore']:
        return 'away'
    else:
        return 'draw'


def calculate_match_result_from_predicted_values(row):
    if row['total_predicted_home_score'] - row['total_predicted_away_score'] > 0.3:
        return 'home'
    elif row['total_predicted_home_score'] - row['total_predicted_away_score'] < -0.3:
        return 'away'
    else:
        return 'draw'


def calculate_success(row):
    if row['predicted_winner'] == row['real_winner']:
        return 1
    else:
        return 0


def calculate_priority(target_percentage, row):
    starter_coefficient = 1000
    percentage_distance = abs(row['handicapPercentage2'] - target_percentage)
    percentage_diff = math.floor(percentage_distance / 5)
    return starter_coefficient * 1.4**row['order'] * 0.7**percentage_diff


def get_spor_toto_week(week_number):
    spor_toto_url = f'http://localhost:8080/sporTotoWeek/{week_number}'
    #spor_toto_url = f'http://localhost:8080/currentSporTotoList/{week_number}'
    df = pd.read_json(spor_toto_url)
    home_tuple_list = [predict_match_result_for_home(row) for index, row in df.iterrows()]
    away_tuple_list = [predict_match_result_for_away(row) for index, row in df.iterrows()]
    df['home_predicted_home_score'], df['home_predicted_away_score'] = [x[0] for x in home_tuple_list], [x[1] for x in home_tuple_list]
    df['away_predicted_home_score'], df['away_predicted_away_score'] = [x[0] for x in away_tuple_list], [x[1] for x in away_tuple_list]
    df['total_predicted_home_score'] = df.apply(lambda row: (row['home_predicted_home_score'] + row['away_predicted_home_score']) / 2, axis=1)
    df['total_predicted_away_score'] = df.apply(lambda row: (row['home_predicted_away_score'] + row['away_predicted_away_score']) / 2, axis=1)
    df['predicted_winner'] = df.apply(lambda row: calculate_match_result_from_predicted_values(row), axis=1)
    df['real_winner'] = df.apply(lambda row: create_winner_column(row), axis=1)
    df['success'] = df.apply(lambda row: calculate_success(row), axis=1)
    df['date'] = df['date'].dt.tz_localize(None)
    #df.to_excel('spor_toto_predicts.xlsx')
    #print(df, 'Total succes:', df['success'].sum())
    #df['success'].sum()
    return df['success'].sum()


def get_home_df(teamId, date, target_home_percentage, target_away_percentage):
    date = date.replace('/', '-')
    match_detail_url = f'http://localhost:8080/matchDetailHome/{teamId}/2021-01-01/{date}'
    df_temp = pd.read_json(match_detail_url)
    df_temp = df_temp.tail(15)
    df_temp['winner'] = df_temp.apply(lambda row: create_winner_column(row), axis=1)
    df_temp["order"] = [i for i in range(1, 1 + df_temp.shape[0])]
    df_temp['priority_home'] = df_temp.apply(lambda row: calculate_priority(target_home_percentage, row), axis=1)
    df_temp['contribution_home'] = df_temp.apply(lambda row: row['homeMatchScore'] * row['priority_home'], axis=1)
    df_temp['priority_away'] = df_temp.apply(lambda row: calculate_priority(target_away_percentage, row), axis=1)
    df_temp['contribution_away'] = df_temp.apply(lambda row: row['awayMatchScore'] * row['priority_away'], axis=1)
    df_temp.drop(['id', 'teamName'], inplace=True, axis=1)
    return df_temp


def get_away_df(teamId, date, target_home_percentage, target_away_percentage):
    date = date.replace('/', '-')
    match_detail_url = f'http://localhost:8080/matchDetailAway/{teamId}/2021-01-01/{date}'
    df_temp = pd.read_json(match_detail_url)
    df_temp = df_temp.tail(15)
    df_temp['winner'] = df_temp.apply(lambda row: create_winner_column(row), axis=1)
    df_temp["order"] = [i for i in range(1, 1 + df_temp.shape[0])]
    df_temp['priority_home'] = df_temp.apply(lambda row: calculate_priority(target_home_percentage, row), axis=1)
    df_temp['contribution_home'] = df_temp.apply(lambda row: row['homeMatchScore'] * row['priority_home'], axis=1)
    df_temp['priority_away'] = df_temp.apply(lambda row: calculate_priority(target_away_percentage, row), axis=1)
    df_temp['contribution_away'] = df_temp.apply(lambda row: row['awayMatchScore'] * row['priority_away'], axis=1)
    df_temp.drop(['id', 'teamName'], inplace=True, axis=1)
    return df_temp


def predict_match_result_for_home(row):
    row['date'] = pd.to_datetime(row['date']).strftime('%Y/%m/%d')
    home_team_df = get_home_df(row['homeId'], row['date'], row['handicapPercentage1'], row['handicapPercentage2'])
    return predict_score(home_team_df)


def predict_match_result_for_away(row):
    row['date'] = pd.to_datetime(row['date']).strftime('%Y/%m/%d')
    away_team_df = get_away_df(row['homeId'], row['date'], row['handicapPercentage1'], row['handicapPercentage2'])
    return predict_score(away_team_df)


if __name__ == '__main__':
    pd.set_option('display.max_columns', None)
    #df_md = create_data_frame(URL)
    #print(predict_score(df_md))
    r = 0
    c = 0
    for i in range(130, 152):
        t = get_spor_toto_week(i)
        r += t
        c += 1
        print(i, t)
    print(r/c)

    """df_md['date'] = df_md['date'].dt.tz_localize(None)
    result_df = predict_future(df_md, ['homeMatchScore', 'awayMatchScore'], ['handicapPercentage1', 'handicapPercentageX', 'handicapPercentage2'])
    winner_df = predict_future(df_md, ['winner'], ['handicapPercentage1', 'handicapPercentageX', 'handicapPercentage2', 'homeMatchScore', 'awayMatchScore'])
    """