from urllib.request import urlopen
import psycopg2 as adapter
import os

# AWS
host = "db-inst.c7wykyqioj0t.us-east-2.rds.amazonaws.com"  # endpoint
dbname = "pokemonDB"
username = "redandgreen"
port = "5432"

# ignore this
_reset = '\033[0m'
_cyan = '\033[96m'
_gray = '\033[90m'


def do_something(c: adapter.extensions.ConnectionInfo):
    print("\nConnect success! (x to exit)")
    while True:
        _input = input(_cyan + "\nEnter query:\n" + _reset)
        if _input == "x":
            break
        try:
            cursor = c.cursor()
            cursor.execute(_input)
            c.commit()

            result = cursor.fetchall()  # print SELECT result, usually empty
            for row in result:
                print(row)

        except Exception as e:
            print(_gray + f"{e}" + _reset)


try:
    connect_string = f"host={host} dbname={dbname} user={username} password={urlopen(
        "https://raw.githubusercontent.com/lucas-z99/td-Xulhezm5lFgt5ocWpxPVYFCxK-b-6U44kgbNzV/main/v579LteW%40l7TA2zNiHqS7vyFVsktv.txt").read().decode('utf-8')[::11]} port={port}"

    connect = adapter.connect(connect_string)
    do_something(connect)
    connect.close()

except Exception as e:
    print(e)
