# RacPlanner

RacPlanner es una aplicación móvil para la organización de horarios académicos.
Permite consultar las carreras ofertadas, seleccionar grupos, visualizar el horario semanal y acceder a la información del postulante, con soporte para persistencia local y funcionamiento offline.

## Descargar la aplicación

La versión más reciente se encuentra disponible en la sección **Releases** del repositorio.

Versión actual: **v1.0.0**

## Requisitos

- Android Studio
- JDK 17
- Gradle Wrapper

## Compilar el proyecto

1. Clonar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Esperar a que Gradle sincronice las dependencias.
4. Ejecutar la aplicación desde Android Studio.

## Generar APK Release

1. Ir a **Build → Generate Signed Bundle / APK**.
2. Seleccionar **APK**.
3. Elegir el keystore.
4. Seleccionar la variante **release**.
5. Finalizar la generación.

El APK generado se encuentra en:

```text
app/release/app-release.apk
```
