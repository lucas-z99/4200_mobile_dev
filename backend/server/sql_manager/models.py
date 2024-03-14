# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class PokeSQL(models.Model):

    class Meta:
        managed = False
        db_table = 'ttt'
        
    id = models.IntegerField(primary_key=True)
    name = models.CharField()
    type0 = models.CharField()
    type1 = models.CharField(blank=True, null=True)
    description = models.CharField()
    height = models.IntegerField()
    weight = models.IntegerField()
    image_url = models.CharField()

    # dict for easy JSON
    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "type0": self.type0,
            "type1": self.type1,
            "description": self.description,
            "height": self.height,
            "weight": self.weight,
            "image_url": self.image_url,
            }

