from urllib.request import urlopen
import psycopg2 as adapter
import threading

# AWS
_host = ""
_dbname = ""
_username = ""
_port = ""
_password = ""

# private
_reset = "\033[0m"  # colors
_cyan = "\033[96m"
_gray = "\033[90m"

file_name = "query.txt"


def query_loop():
    while True:
        print(_cyan + "\nEnter query:", _reset)
        _input = input()

        if _input == "import data":  # add all data from .txt
            auto_import_data()
        else:
            handle_query(_input)


def handle_query(query: str):
    print(_gray)
    try:
        cursor = conn.cursor()
        cursor.execute(query)
        conn.commit()

        result = cursor.fetchall()  # print SELECT result, usually empty
        for r in result:
            print(r)

    except Exception as e:
        print(f"{e}")
    finally:
        cursor.close()
        print(_reset)


def auto_import_data():

    def _import(query):
        handle_query(query)

    threads = []
    with open(file_name, "r") as file:
        for q in file.readlines():
            t = threading.Thread(target=_import, args=(q,))
            threads.append(t)
            t.start()
    for t in threads:
        t.join()
    print("Complete!")


#   --------------------------------------------------------------------------------------------------

try:
    conn_string = f"host={_host} dbname={_dbname} user={_username} password={_password} port={_port}"
    conn = adapter.connect(conn_string)

    print("\nConnect success!")

    query_loop()

except Exception as e:
    print(e)
finally:
    conn.close()


#   SQL cheat sheet   -------------------------------------------------------------------------------

# SELECT column_name FROM information_schema.columns WHERE table_name = 'ttt' ORDER BY ordinal_position
# SELECT * FROM ttt ORDER BY id ASC
# SELECT COUNT(*) FROM ttt

# ALTER TABLE ttt ADD COLUMN "type1" TEXT
# ALTER TABLE ttt ADD COLUMN "height" REAL
# ALTER TABLE ttt DROP COLUMN "number"
# ALTER TABLE ttt RENAME COLUMN "def" TO "defense"

# UPDATE ttt SET attack = 999 WHERE id = 123
# DELETE FROM ttt WHERE id = 123
