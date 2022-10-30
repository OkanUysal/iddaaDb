import pandas as pd

list_of_colons = []
exception_max_limit = 5
exception_min_limit = 3
draw_max_limit = 5
draw_min_lim = 3
real_winner = []

def get_current_week_coupon():
    return pd.read_excel("output/a.xlsx", sheet_name='cost_1024_i_10_j_0')


def get_next_week_data_frame(week_number):
    spor_toto_url = f'http://localhost:8080/currentSporTotoList/{week_number}'
    return pd.read_json(spor_toto_url)


def get_success_rate(match_list):
    r = 0
    for i in range(len(real_winner)):
        if int(match_list[i]) == real_winner[i]:
            r += 1
    return r


def get_combinations_for_week(rate_list, coupon_list, draw_count, exception_count, current_list):
#    if draw_count > draw_max_limit or exception_count > exception_max_limit:
#        return
    if len(current_list) == 15:
        if draw_count >= draw_min_lim and exception_count >= exception_min_limit and draw_count <= draw_max_limit and exception_count <= exception_max_limit:
            current_list.append(11111)
            current_list.append(draw_count)
            current_list.append(exception_count)
            #current_list.append(get_success_rate(current_list))
            list_of_colons.append(current_list)
        else:
            current_list.append(100000)
            current_list.append(draw_count)
            current_list.append(exception_count)
            current_list.append(get_success_rate(current_list))
            #list_of_colons.append(current_list)
        return
    variations = str(coupon_list[0]).split('-')
    for s in variations:
        temp_rate_list = [rate_list[0][1:], rate_list[1][1:], rate_list[2][1:]]
        temp_coupon_list = coupon_list[1:]
        s = s.strip()
        temp_current_list = current_list.copy()
        temp_current_list.append(s)
        if s == '1':
            if rate_list[0][0] > rate_list[2][0]:
                get_combinations_for_week(temp_rate_list, temp_coupon_list, draw_count, exception_count, temp_current_list)
            else:
                get_combinations_for_week(temp_rate_list, temp_coupon_list, draw_count, exception_count + 1, temp_current_list)
        elif s == '0':
            get_combinations_for_week(temp_rate_list, temp_coupon_list, draw_count + 1, exception_count, temp_current_list)
        else:
            if rate_list[2][0] > rate_list[0][0]:
                get_combinations_for_week(temp_rate_list, temp_coupon_list, draw_count, exception_count, temp_current_list)
            else:
                get_combinations_for_week(temp_rate_list, temp_coupon_list, draw_count, exception_count + 1, temp_current_list)


if __name__ == '__main__':
    df = get_next_week_data_frame(154)
    coupon_df = get_current_week_coupon()
    #real_winner = coupon_df['real_winner'].tolist()
    get_combinations_for_week([df['handicapPercentage1'].tolist(), df['handicapPercentageX'].tolist(), df['handicapPercentage2'].tolist()], coupon_df['toto_results'].tolist(), 0, 0, [])
    df_output = pd.DataFrame(list_of_colons)
    df_output.to_excel('output/colon_combinations.xlsx')
    print(len(list_of_colons))
    for x in list_of_colons:
        print(x)
