from urllib.request import urlopen
import psycopg2 as adapter
import os

# AWS
host = "db-inst.c7wykyqioj0t.us-east-2.rds.amazonaws.com"
dbname = "pokemonDB"
username = "redandgreen"
port = "5432"
password = "MA2DF7lLx4!THMAgg$ccWeeYgBKG3wcyzJAKLcge_rDcDn-5hsA4DG_26BE5CWoD"

# ignore this
_reset = '\033[0m'
_cyan = '\033[96m'
_gray = '\033[90m'


def do_something(connect: adapter.extensions.ConnectionInfo):
    while True:

        print(_cyan + "Enter query:" + _reset)
        _input = input()

        try:
            cursor = connect.cursor()
            cursor.execute(_input)
            connect.commit()

            result = cursor.fetchall()  # print SELECT result, usually empty
            for r in result:
                print(r)

        except Exception as e:
            print(_gray + f"{e}" + _reset)


try:
    connect_string = f"host={host} dbname={dbname} user={
        username} password={password} port={port}"

    connect = adapter.connect(connect_string)
    print("\nConnect success!\n")

    do_something(connect)
    connect.close()

except Exception as e:
    print(e)


#   SQL cheat sheet   -------------------------------------------------------------------------------

# ALTER TABLE ttt ADD COLUMN "type1" TEXT
# ALTER TABLE ttt DROP COLUMN "number"
# ALTER TABLE ttt RENAME COLUMN "def" TO "defense"

# print col names
# SELECT column_name FROM information_schema.columns WHERE table_name = 'ttt' ORDER BY ordinal_position





