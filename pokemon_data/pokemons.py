import os
from urllib.request import urlopen
import requests
import threading
import datetime


#  setting   -----------------------------------------------------

base_url = "https://pokeapi.co/api/v2/pokemon/"
base_url_description = "https://pokeapi.co/api/v2/pokemon-species/"  # For description in pokemon[flavor_text_entries][0][flavor_text]

count = 151  # total 151
file_name = "query"
file_ext = ".txt"
generate_file = True
debug_print = False


#   get URL   -----------------------------------------------------
pokemon_url_list = []
pokemon_url_description_list = []

for i in range(count):
    pokemon_url_list.append(base_url + str(i + 1))
    pokemon_url_description_list.append(base_url_description + str(i + 1))


# fetch data + generate SQL query
def fetch_data(index: int, url: str, url_description: str):

    # data
    pokemon = requests.get(url).json()

    name = pokemon["forms"][0]["name"]
    type0 = pokemon["types"][0]["type"]["name"]
    type1 = "" if len(pokemon["types"]) < 2 else pokemon["types"][1]["type"]["name"]
    height = pokemon["height"]
    weight = pokemon["weight"]
    image = pokemon["sprites"]["other"]["official-artwork"]["front_default"]

    # description
    pokemon_description = requests.get(url_description).json()

    for entries in pokemon_description["flavor_text_entries"]:
        if entries["language"]["name"] == "en":
            desc = entries["flavor_text"]

            desc = desc.replace("\n", " ")
            desc = desc.replace("\x0c", " ")  # form feed
            desc = desc.replace("'", "''")  # single ' will escape SQL query
            # desc = desc.replace("POKéMON", "Pokémon")
            desc = desc.replace("POKéMON", "Pokemon")
            break

    query = "INSERT INTO ttt (id, name, type0, type1, height, weight, description, image_url) VALUES ("
    query += f"'{index}', '{name}', '{type0}', '{type1}', {height}, {weight}, '{desc}', '{image}')"

    global query_list
    query_list.append((index, query))


#   fetch all with threads   -----------------------------------------------------
threads = []
query_list = []

for i in range(count):
    index = i + 1  # prevent late binding ruins our index
    t = threading.Thread(
        target=fetch_data,
        args=(
            index,
            pokemon_url_list[i],
            pokemon_url_description_list[i],
        ),
    )
    threads.append(t)


fetch_count = 0
for t in threads:
    t.start()  # no race?
    fetch_count += 1
    print(f"\rfetching data... {fetch_count}", end="")


#   back to main thread   -----------------------------------------------------
for t in threads:
    t.join()

query_list.sort(key=lambda x: x[0])  # sort by index, so it's less ugly

if generate_file:
    print("\nwrite to file...")

    timestamp = str(datetime.datetime.now().timestamp())
    _name = file_name + "_" + timestamp + file_ext

    # write to file
    with open(_name, "w", encoding="utf-8") as file:
        for q in query_list:
            file.write(q[1] + "\n")

# if debug_print:
#     for q in query_list:
#         print("query = ", q[1] + "\n")
#     print("total count:", len(query_list))

print("done!")
