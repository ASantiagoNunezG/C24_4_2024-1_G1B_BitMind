from rest_framework import serializers
from .models import Usuario, Carrera, Ciclo, Curso, Foro, Comentario, Anuncio, Archivo, Publicacion, Valoracion

class UsuarioSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Usuario
        fields = '__all__'

class CarreraSerializer(serializers.ModelSerializer):
    class Meta:
        model = Carrera
        fields = '__all__'

class CicloSerializer(serializers.ModelSerializer):
    class Meta:
        model = Ciclo
        fields = '__all__'
        

class CursoSerializer(serializers.ModelSerializer):
    ciclo_nombre = serializers.ReadOnlyField(source='id_ciclo.nombre')
    carrera_nombre = serializers.ReadOnlyField(source='id_carrera.nombre')
    
    class Meta:
        model = Curso
        fields = '__all__'
        

class ForoSerializer(serializers.ModelSerializer):
    usuario_nombre = serializers.ReadOnlyField(source='id_usuario.nombres')

    class Meta:
        model = Foro
        fields = '__all__'
        

class ComentarioSerializer(serializers.ModelSerializer):
    usuario_nombre = serializers.ReadOnlyField(source='id_usuario.nombres')
    foro_nombre = serializers.ReadOnlyField(source='id_foro.titulo')
    
    class Meta:
        model = Comentario
        fields = '__all__'
        

class AnuncioSerializer(serializers.ModelSerializer):
    usuario_nombre = serializers.ReadOnlyField(source='id_usuario.nombres')
    
    class Meta:
        model = Anuncio
        fields = '__all__'
        

class PublicacionSerializer(serializers.ModelSerializer):
    usuario_nombre = serializers.ReadOnlyField(source='id_usuario.nombres')
    curso_nombre = serializers.ReadOnlyField(source='id_curso.nombre')

    class Meta:
        model = Publicacion
        fields = '__all__'
        
        
class ArchivoSerializer(serializers.ModelSerializer):
    publicacion_nombre = serializers.ReadOnlyField(source='id_publicacion.titulo')
        
    class Meta:
        model = Archivo
        fields = '__all__'
        
        
class ValoracionSerializer(serializers.ModelSerializer):
    usuario_nombre = serializers.ReadOnlyField(source='id_usuario.nombres')
    publicacion_nombre = serializers.ReadOnlyField(source='id_publicacion.titulo')

    class Meta:
        model = Valoracion
        fields = '__all__'
        