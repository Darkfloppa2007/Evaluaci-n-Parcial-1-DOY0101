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

# Evaluaci-n-Parcial-2-DOY0101

# Microservicio de Gestión de Productos - Arquitectura Contenerizada y Pipeline CI/CD

Este proyecto implementa un microservicio basado en Spring Boot (Java 21) utilizando el patrón arquitectónico Controller-Service-Repository (CSR), completamente automatizado bajo estándares Cloud-Native y prácticas DevSecOps.

## 🚀 Pipeline de CI/CD y Gobernanza

El flujo de integración y entrega continua está automatizado mediante GitHub Actions (`.github/workflows/ci-cd.yml`) y se divide en dos etapas críticas:

1. **Integración Continua (CI - Calidad y Estabilidad):**
   - Compila el proyecto con Maven y ejecuta las pruebas unitarias y de integración de forma aislada.
   - Levanta un contenedor efímero de MySQL en el entorno virtual para verificar la persistencia real de los datos.

2. **Entrega Continua (CD - Seguridad y Escalabilidad):**
   - **Gobernanza:** Realiza un análisis estático de código (SAST) con SonarQube y un escaneo de vulnerabilidades en la imagen Docker con Trivy. El pipeline está configurado para **bloquearse inmediatamente** (`exit code 1`) si se detecta un fallo de seguridad crítico.
   - **Despliegue:** Una vez validada, la imagen se despliega en un clúster simulado de Kubernetes aplicando políticas de escalabilidad automática (HPA) basadas en el consumo de CPU.

## 🔍 Garantía de Trazabilidad

La trazabilidad de extremo a extremo se garantiza asociando de manera única el **Git Commit SHA** (`${{ github.sha }}`) generado en IntelliJ con el contenedor en producción. Cada imagen construida se etiqueta con este ID alfanumérico, permitiendo auditar con precisión qué versión del código fuente está corriendo en el entorno cloud en cualquier momento.

# Evaluaci-n-Parcial-3-DOY0101

📊 Monitoreo Avanzado, Orquestación y Dashboard Unificado (IE1, IE2, IE3, IE4, IE5, IE6)

Para cumplir con los estándares de observabilidad y gobernanza en entornos productivos distribuidos, el ecosistema se expande integrando herramientas de recolección de métricas en tiempo real y orquestación elástica de contenedores.
1. ⚙️ Infraestructura de Monitoreo y Observabilidad (IE1)
El microservicio expone de forma nativa sus métricas de rendimiento interno a través de Spring Boot Actuator en formato compatible con Prometheus. El ecosistema de telemetría local y cloud está compuesto por:

Prometheus: Recolecta de forma proactiva (pull mechanism) las métricas expuestas en el endpoint /actuator/prometheus, monitorizando hilos de ejecución, conexiones a la base de datos y consumo de memoria de la JVM.

Grafana: Centraliza y unifica visualmente los datos recolectados por Prometheus, permitiendo correlacionar el estado de la infraestructura con el comportamiento de la lógica de negocio.

2. ☸️ Despliegue y Orquestación en Kubernetes Cloud (IE2)
La transición desde un entorno de desarrollo local hacia la nube (AWS EKS / Google GKE) se realiza mediante manifiestos declarativos de Kubernetes, garantizando la alta disponibilidad del microservicio. El clúster se gestiona con:

Deployments: Mantiene un estado de replicación constante de 2 Pods simultáneos para mitigar caídas de servicio.

Horizontal Pod Autoscaler (HPA): Monitorea dinámicamente el clúster. Si el uso de CPU o memoria por Pod supera el 70%, Kubernetes escala automáticamente el número de instancias para soportar el incremento de tráfico de forma autónoma.

Services (LoadBalancer): Expone un punto de enlace único y público provisto por el proveedor de nube para balancear las peticiones entrantes entre las instancias activas.

3. 🖥️ Dashboard Unificado de Desempeño y Calidad (IE3)

Categoría de Métrica,Métrica Clave Evaluada,Fuente de Origen,Consulta Técnica (PromQL / API)
Tiempo de Despliegue,Duración de ejecución del pipeline,GitHub Actions API,workflow_run_duration_seconds
Cobertura de Pruebas,Porcentaje de código cubierto por tests,SonarQube Quality Gate,API Externa (sonar.qualitygate)
Uso de Infraestructura,Consumo instantáneo de CPU y Memoria,Kubernetes (K8s) Pods,system_cpu_usagejvm_memory_used_bytes
Salud del Sistema,Tasa de excepciones y errores críticos,Spring Actuator / Logback,"sum(rate(logback_events_total{level=""error""}[5m]))" 

4. 🛡️ Políticas de Cumplimiento y Gobernanza de Código (IE5)
La gobernanza del repositorio se automatiza mediante reglas estrictas que impiden la inyección de código defectuoso en la rama productiva:

-Branch Protection en GitHub: Se prohíbe el push directo a main. Las fusiones de código exigen obligatoriamente la apertura de un Pull Request y la aprobación exitosa (círculo verde) de todas las etapas del pipeline de CI/CD.

-Inspección SAST Estricta: SonarQube evalúa el cumplimiento de estándares, deuda técnica y porcentaje mínimo de cobertura (fijado en 80% como umbral aceptable).

5. 💡 Integración en el Pipeline CI/CD y Toma de Decisiones Técnicas (IE4)
La recopilación analítica de estos datos proporciona fundamentos métricos para la toma de decisiones del equipo de ingeniería:

-Decisión de Infraestructura (Capacidad): Si el panel de Grafana revela que la memoria de la JVM (jvm_memory_used_bytes) roza persistentemente sus límites, se decide técnicamente reajustar los Limits y Requests del manifiesto de Kubernetes.

-Decisión de Código (Deuda Técnica): Un incremento en la tasa de eventos de error en Logback (logback_events_total) posterior a un despliegue sirve como indicador inequívoco para congelar el desarrollo de nuevas features y priorizar tareas de refactorización o corrección inmediata.

-Decisión de Proceso (Optimización): Si la métrica workflow_run_duration_seconds supera los umbrales esperados, se evalúa la paralelización de tests unitarios o la optimización en las capas de caché de Maven y Docker.

6. 🛑 Parada de Emergencia ante Fallas Críticas (IE6)
El pipeline de integración continua está configurado como un guardián automatizado infalible. Se garantiza que ante una violación de las políticas de seguridad o calidad el flujo se interrumpe por completo, cancelando el proceso de entrega de la siguiente forma:

-Bloqueo por Calidad (SonarQube): Al incluir la propiedad -Dsonar.qualitygate.wait=true, si el código disminuye su cobertura o introduce code smells graves, SonarQube retorna un código de salida fallido (exit 1), rompiendo el pipeline de GitHub Actions inmediatamente y previniendo la compilación del contenedor.

-Bloqueo por Seguridad (Trivy Vulnerability Scanner): Durante la etapa de CD, la herramienta Trivy examina las capas del contenedor Docker. Al configurarse con el parámetro exit-code: '1' ante gravedades HIGH o CRITICAL, cualquier vulnerabilidad conocida (CVE) detendrá el pipeline al instante, bloqueando los comandos docker push y kubectl apply. De esta manera, el software vulnerable nunca llega a desplegarse en el clúster de Kubernetes en producción.


                    
