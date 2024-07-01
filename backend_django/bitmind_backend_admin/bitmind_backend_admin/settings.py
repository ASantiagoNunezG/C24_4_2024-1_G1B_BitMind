"""
Django settings for bitmind_backend_admin project.

Generated by 'django-admin startproject' using Django 5.0.4.

For more information on this file, see
https://docs.djangoproject.com/en/5.0/topics/settings/

For the full list of settings and their values, see
https://docs.djangoproject.com/en/5.0/ref/settings/
"""

from pathlib import Path
from datetime import timedelta

# Build paths inside the project like this: BASE_DIR / 'subdir'.
BASE_DIR = Path(__file__).resolve().parent.parent


# Quick-start development settings - unsuitable for production
# See https://docs.djangoproject.com/en/5.0/howto/deployment/checklist/

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'django-insecure-9q8e$uia=)y#6g45z_jtwnrbpa2^i3bx7gp3z+8s8-3+z#qx^!'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

ALLOWED_HOSTS = []


# Application definition

INSTALLED_APPS = [
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'backend',
    'rest_framework',
    'corsheaders',
    'rest_framework_simplejwt',
    'storages'
]

MIDDLEWARE = [
    'django.middleware.security.SecurityMiddleware',
    'django.contrib.sessions.middleware.SessionMiddleware',
    "corsheaders.middleware.CorsMiddleware",
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
]

ROOT_URLCONF = 'bitmind_backend_admin.urls'

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [],
        'APP_DIRS': True,
        'OPTIONS': {
            'context_processors': [
                'django.template.context_processors.debug',
                'django.template.context_processors.request',
                'django.contrib.auth.context_processors.auth',
                'django.contrib.messages.context_processors.messages',
            ],
        },
    },
]

WSGI_APPLICATION = 'bitmind_backend_admin.wsgi.application'


# Database
# https://docs.djangoproject.com/en/5.0/ref/settings/#databases




# Password validation
# https://docs.djangoproject.com/en/5.0/ref/settings/#auth-password-validators

AUTH_PASSWORD_VALIDATORS = [
    {
        'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',
    },
    {
        'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',
    },
]


# Internationalization
# https://docs.djangoproject.com/en/5.0/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_TZ = True


# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/5.0/howto/static-files/

STATIC_URL = 'static/'

# Default primary key field type
# https://docs.djangoproject.com/en/5.0/ref/settings/#default-auto-field

DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'

REST_FRAMEWORK = {
    'DEFAULT_AUTHENTICATION_CLASSES': [
        'rest_framework_simplejwt.authentication.JWTAuthentication',
    ],
}

#Configuraciones para la base de datos
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'bitmind3',
        'USER': 'root',
        'PASSWORD': '12345',
        'HOST': 'localhost',  # O la dirección de tu servidor MySQL
        'PORT': '3306',       # Puerto de MySQL
    }
}


#CONFIGURACIONES PARA EL PROYECTO

#import os

# Define the base directory of the project
#BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

# Go one level up from BASE_DIR and then to 'Materiales'
#MEDIA_ROOT = os.path.join(os.path.dirname(BASE_DIR), 'Materiales')
# Go two levels up from BASE_DIR and then to 'Materiales'
#MEDIA_ROOT = os.path.join(os.path.dirname(os.path.dirname(BASE_DIR)), 'Materiales')

#Configuraciones para acceder a las imagenes a través de la ruta
#MEDIA_URL = '/media/'

#Listo para trabajar con REACT
CORS_ALLOWED_ORIGINS = ["http://localhost:5173"]
CORS_ALLOW_CREDENTIALS = True
CSRF_TRUSTED_ORIGINS = ["http://localhost:5173"]

SIMPLE_JWT = {
    "ACCESS_TOKEN_LIFETIME": timedelta(minutes=1400),
    "REFRESH_TOKEN_LIFETIME": timedelta(days=1),
}

#AWS S3
AWS_ACCESS_KEY_ID = 'AKIAYS2NTYQS5TB37ZV3'
AWS_SECRET_ACCESS_KEY = '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'
AWS_STORAGE_BUCKET_NAME = 'bucketbitmind'
AWS_S3_REGION_NAME = 'us-east-2'
AWS_S3_CUSTOM_DOMAIN = 'bucketbitmind.s3.amazonaws.com'

# Configuración del almacenamiento predeterminado
DEFAULT_FILE_STORAGE = 'storages.backends.s3boto3.S3Boto3Storage'