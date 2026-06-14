# Evaluaci-n-Parcial-1-DOY0101
El encargo debe incluir los siguientes apartados:
1. Crean un repositorio Git en GitHub con las siguientes ramas: main, develop, feature/<nombre> y hotfix/<nombre>. (IE5)
2. Implementan GitFlow o trunk-based development, justificando su elección en el README del repositorio. (IE1)
3. Simulan un desarrollo colaborativo integrando al menos 2 cambios tipo feature y 1 tipo hotfix mediante pull requests. (IE2)
4. Documentan en un archivo README.md o wiki las convenciones de commits, flujos de merge, naming de ramas y estrategias de
revisión. (IE5)
5. Configuran al menos una acción básica de GitHub Actions que se ejecute con cada push a develop y pull request a main. (IE3/IE4)



📘 Convención de Nombres para Ramas usando Gitflow
📌 Propósito

Este documento establece una convención clara, consistente y escalable para nombrar ramas en un repositorio Git que utiliza la metodología Gitflow. Su objetivo es mejorar la colaboración, facilitar la trazabilidad de cambios y reducir errores en el ciclo de desarrollo.

🌳 Modelo Gitflow

Gitflow define un conjunto de ramas con roles específicos:

Rama	Propósito
main	Código en producción
develop	Integración de desarrollo
feature	Nuevas funcionalidades
release	Preparación de versiones
hotfix	Correcciones urgentes en producción
bugfix	Corrección de errores en desarrollo (opcional)
🧩 Estructura General de Nombres

Todas las ramas deben seguir esta estructura:

<tipo>/<identificador>-<descripcion>
Componentes:
tipo: Categoría de la rama (feature, hotfix, etc.)
identificador: ID del ticket (Jira, GitHub Issues, etc.)
descripcion: Breve descripción en minúsculas separada por guiones
🚀 Tipos de Ramas y Convenciones
1. 🔵 Rama main
Contiene código listo para producción.
Solo recibe merges desde release y hotfix.

Ejemplo:

main
2. 🟢 Rama develop
Rama base para el desarrollo continuo.
Integra todas las funcionalidades terminadas.

Ejemplo:

develop
3. ✨ Ramas feature

Se utilizan para desarrollar nuevas funcionalidades.

Formato:

feature/<ticket>-<descripcion>

Ejemplos:

feature/JIRA-123-login-usuario
feature/456-integracion-pasarela-pago
feature/github-issue-78-dashboard-admin

Reglas:

Se crean desde develop.
Se eliminan después del merge.
Deben representar una única funcionalidad.
4. 🧪 Ramas bugfix (opcional)

Correcciones de errores detectados en desarrollo.

Formato:

bugfix/<ticket>-<descripcion>

Ejemplos:

bugfix/JIRA-234-error-validacion-email
bugfix/512-fix-navbar-responsive

Reglas:

Se crean desde develop.
Se integran nuevamente en develop.
5. 📦 Ramas release

Preparación de una nueva versión para producción.

Formato:

release/<version>

Ejemplos:

release/1.0.0
release/2.3.1

Reglas:

Se crean desde develop.
Solo se permiten:
Correcciones menores
Actualización de versiones
Documentación
Se fusionan en:
main
develop
6. 🚑 Ramas hotfix

Correcciones urgentes en producción.

Formato:

hotfix/<version>-<descripcion>

Ejemplos:

hotfix/1.0.1-fix-login
hotfix/2.0.2-error-calculo-total

Reglas:

Se crean desde main.
Se fusionan en:
main
develop
Deben ser cambios mínimos y críticos.
🔢 Versionado

Se recomienda usar Versionado Semántico (SemVer):

MAJOR.MINOR.PATCH
Tipo de cambio	Ejemplo
Cambio mayor	2.0.0
Nueva funcionalidad	1.1.0
Corrección de bug	1.0.1
🧾 Buenas Prácticas
📌 Nombres
Usar minúsculas.
Separar palabras con -.
Evitar caracteres especiales (_, espacios, acentos).
Mantener nombres cortos pero descriptivos.
📌 Identificadores
Incluir ID del ticket siempre que sea posible.
Permite trazabilidad entre código y gestión de tareas.
📌 Alcance
Una rama = un propósito.
Evitar mezclar features y fixes en una misma rama.
📌 Limpieza
Eliminar ramas después del merge.
Mantener el repositorio ordenado.
🔄 Flujo de Trabajo
🧪 Desarrollo de Feature
develop → feature → develop
Crear rama desde develop
Desarrollar funcionalidad
Crear Pull Request
Merge a develop
Eliminar rama
📦 Release
develop → release → main + develop
Crear rama release
Ajustes finales
Merge a main
Merge de vuelta a develop
Crear tag de versión
🚑 Hotfix
main → hotfix → main + develop
Crear rama desde main
Corregir error crítico
Merge a main
Merge a develop
Tag de nueva versión
🏷️ Tags (Recomendado)

Para marcar versiones en producción:

v<version>

Ejemplos:

v1.0.0
v2.1.3
🔐 Convenciones para Pull Requests
Título claro:
[FEATURE] JIRA-123 - Login de usuario
Tipos:
[FEATURE]
[BUGFIX]
[HOTFIX]
[RELEASE]
Descripción debe incluir:
Qué se hizo
Cómo probarlo
Evidencia (si aplica)
⚠️ Errores Comunes a Evitar
❌ feature/nueva-cosa (sin contexto)
❌ fix123 (sin tipo ni descripción)
❌ Feature/Login (uso de mayúsculas)
❌ Mezclar múltiples cambios en una sola rama
🧪 Ejemplos Completos
feature/JIRA-101-registro-usuario
bugfix/JIRA-202-error-validacion-password
release/1.4.0
hotfix/1.4.1-fix-token-expirado
