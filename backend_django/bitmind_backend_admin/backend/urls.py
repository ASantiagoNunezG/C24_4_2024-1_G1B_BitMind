from django.urls import include, path
from rest_framework.routers import DefaultRouter
from .views import (
    UsuarioViewSet, CarreraViewSet, CicloViewSet, CursoViewSet, ForoViewSet,
    ComentarioViewSet, AnuncioViewSet, ArchivoViewSet, PublicacionViewSet, ValoracionViewSet
)

router = DefaultRouter()
router.register(r'usuarios', UsuarioViewSet)
router.register(r'carreras', CarreraViewSet)
router.register(r'ciclos', CicloViewSet)
router.register(r'cursos', CursoViewSet)
router.register(r'foros', ForoViewSet)
router.register(r'comentarios', ComentarioViewSet)
router.register(r'anuncios', AnuncioViewSet)
router.register(r'publicaciones', PublicacionViewSet)
router.register(r'archivos', ArchivoViewSet)
router.register(r'valoraciones', ValoracionViewSet)

urlpatterns = [
    path('', include(router.urls)),
]