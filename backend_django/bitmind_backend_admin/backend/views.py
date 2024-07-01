from rest_framework import viewsets
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from .models import Usuario, Carrera, Ciclo, Curso, Foro, Comentario, Anuncio, Archivo, Publicacion, Valoracion
from .serializers import (
    UsuarioSerializer, CarreraSerializer, CicloSerializer, CursoSerializer,
    ForoSerializer, ComentarioSerializer, AnuncioSerializer, ArchivoSerializer,
    PublicacionSerializer, ValoracionSerializer
)

class UsuarioViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer

class CarreraViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Carrera.objects.all()
    serializer_class = CarreraSerializer

class CicloViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Ciclo.objects.all()
    serializer_class = CicloSerializer

class CursoViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Curso.objects.all()
    serializer_class = CursoSerializer

class ForoViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Foro.objects.all()
    serializer_class = ForoSerializer

class ComentarioViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Comentario.objects.all()
    serializer_class = ComentarioSerializer

class AnuncioViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Anuncio.objects.all()
    serializer_class = AnuncioSerializer

class PublicacionViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Publicacion.objects.all()
    serializer_class = PublicacionSerializer
    
class ArchivoViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Archivo.objects.all()
    serializer_class = ArchivoSerializer

class ValoracionViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Valoracion.objects.all()
    serializer_class = ValoracionSerializer
