from urllib.request import urlopen 
import json

base_url = 'https://pokeapi.co/api/v2/pokemon/'

pokemon_url_list = []

for i in range(151):
  pokemon_url_list.append(base_url + str(i+1))

print(pokemon_url_list)