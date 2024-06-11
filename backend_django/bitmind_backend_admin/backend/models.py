from django.db import models
from django.core.validators import MinValueValidator, MaxValueValidator

class Usuario(models.Model):
    id_usuario = models.AutoField(primary_key=True, db_column='id_usuario')
    nombres = models.CharField(max_length=255)
    correo = models.EmailField(unique=True)
    clave = models.CharField(max_length=255)
    imagen = models.ImageField(upload_to='usuarios/')

    class Meta:
        managed = True
        db_table = 'usuarios'
        
    def __str__(self):
        return self.nombres

class Carrera(models.Model):
    id_carrera = models.AutoField(primary_key=True, db_column="id_carrera")
    nombre = models.CharField(max_length=255)

    class Meta:
        managed = True
        db_table = 'carreras'
        
    def __str__(self):
        return self.nombre

class Ciclo(models.Model):
    id_ciclo = models.AutoField(primary_key=True, db_column="id_ciclo")
    nombre = models.CharField(max_length=255)
    id_carrera = models.ForeignKey(Carrera, on_delete=models.CASCADE,db_column='id_carrera')

    class Meta:
        managed = True
        db_table = 'ciclos'
        
    def __str__(self):
        return self.nombre
    
class Curso(models.Model):
    id_curso = models.AutoField(primary_key=True, db_column="id_curso")
    nombre = models.CharField(max_length=255)
    id_ciclo = models.ForeignKey(Ciclo, on_delete=models.CASCADE,db_column='id_ciclo')

    class Meta:
        managed = True
        db_table = 'cursos'
    
    def __str__(self):
        return self.nombre

class Foro(models.Model):
    id_foro = models.AutoField(primary_key=True, db_column="id_foro")
    titulo = models.CharField(max_length=255)
    contenido = models.TextField()
    fecha_creacion = models.DateTimeField()
    id_usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE,db_column='id_usuario')

    class Meta:
        managed = True
        db_table = 'foros'
    
    def __str__(self):
        return self.titulo

class Comentario(models.Model):
    id_comentario = models.AutoField(primary_key=True, db_column="id_comentario")
    contenido = models.TextField()
    fecha_creacion = models.DateTimeField()
    id_usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE,db_column='id_usuario')
    id_foro = models.ForeignKey(Foro, on_delete=models.CASCADE, db_column='id_foro')

    class Meta:
        managed = True
        db_table = 'comentarios'

class Anuncio(models.Model):
    id_anuncio = models.AutoField(primary_key=True, db_column="id_anuncio")
    titulo = models.CharField(max_length=255)
    descripcion = models.TextField()
    imagen = models.ImageField(upload_to='anuncios/')
    fecha_creacion = models.DateTimeField()
    id_usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, db_column='id_usuario')

    class Meta:
        managed = True
        db_table = 'anuncios'


class Publicacion(models.Model):
    id_publicacion = models.AutoField(primary_key=True, db_column="id_publicacion")
    titulo = models.CharField(max_length=255)
    descripcion = models.TextField()
    imagen = models.ImageField(upload_to='publicaciones/')
    vistas = models.IntegerField(default=0)
    fecha_creacion = models.DateTimeField()
    fecha_modificacion = models.DateTimeField()
    id_curso = models.ForeignKey(Curso, on_delete=models.CASCADE, db_column='id_curso')
    id_usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, db_column='id_usuario')

    class Meta:
        managed = True
        db_table = 'publicaciones'
    
    def __str__(self):
        return self.titulo

class Archivo(models.Model):
    id_archivo = models.AutoField(primary_key=True, db_column="id_archivo")
    nombre = models.CharField(max_length=255)
    url = models.FileField(upload_to='archivos/')
    tipo = models.CharField(max_length=100)
    id_publicacion = models.ForeignKey(Publicacion, on_delete=models.CASCADE, db_column='id_publicacion')

    class Meta:
        managed = True
        db_table = 'archivos'

class Valoracion(models.Model):
    id_valoracion = models.AutoField(primary_key=True, db_column="id_valoracion")
    escala = models.IntegerField(
        validators=[MinValueValidator(1), MaxValueValidator(10)]
    )
    fecha_creacion = models.DateTimeField()
    id_usuario = models.ForeignKey(Usuario, on_delete=models.CASCADE, db_column='id_usuario')
    id_publicacion = models.ForeignKey(Publicacion, on_delete=models.CASCADE, db_column='id_publicacion')

    class Meta:
        managed = True
        db_table = 'valoraciones'
