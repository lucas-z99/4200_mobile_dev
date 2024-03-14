from django.shortcuts import render
from django.http import JsonResponse
from django.http import HttpResponse
from django.http import HttpResponseBadRequest # handle bad request

from .models import PokeSQL


# SQL
def get_pokemon_by_id(request, id):
    try:
        item = PokeSQL.objects.get(pk=id)
        return JsonResponse(item.to_dict(), safe=False)
    except PokeSQL.DoesNotExist:
        return HttpResponse('Not found', status=404, content_type='text/plain')

def get_pokemon_by_name(request, name):
    try:
        item = PokeSQL.objects.get(name=name)
        return JsonResponse(item.to_dict(),safe=False)
    except PokeSQL.DoesNotExist:
        return HttpResponse('Not found', status=404, content_type='text/plain')
        

# bad request
def handle_404(request, exception):
    return HttpResponse('Error 404', content_type='text/plain', status=404)


# test page
def server_test(request): 
    return HttpResponse('Okay fire, is there any BWILA in there?', content_type='text/plain')





# def get_whole_table(request):
#     data = list(PokeSQL.objects.values())
#     return JsonResponse(data, safe=False)  # return the whole table
