import requests
import json

URL = "http://localhost:8080/league/1"


if __name__ == '__main__':
    r = requests.get(url=URL)
    data = r.json()
    print(data)

