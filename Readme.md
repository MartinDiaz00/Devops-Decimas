# Supermercado Microservicio - DevOps

Proyecto cambiado desde Supermercado a un microservicio simple de supermercado.
La aplicación permite listar productos, probar conexión con ping y realizar una compra básica.

## Qué contiene

- 1 microservicio Spring Boot.
- Frontend simple en `src/main/resources/static/index.html`.
- Endpoints REST para productos, compra y ping.
- Dockerfile para construir la imagen.
- Manifiesto Kubernetes con `LoadBalancer` para mostrar el frontend por DNS/IP.
- GitHub Actions para CI y despliegue en EKS.

## Endpoints importantes

```bash
GET /api/supermercado/ping
GET /api/supermercado/productos
POST /api/supermercado/comprar
```

Ejemplo de compra:

```json
{
  "productoId": 1,
  "cantidad": 2
}
```

## Ejecutar local

```bash
mvn clean package -DskipTests
mvn spring-boot:run
```

Abrir:

```text
http://localhost:8080
```

## Ejecutar con Docker

```bash
docker build -t supermercado-microservicio .
docker run -p 8080:8080 supermercado-microservicio
```

Abrir:

```text
http://localhost:8080
```

## Ejecutar con Docker Compose

```bash
docker compose up --build
```

## Despliegue en Kubernetes / EKS

El archivo importante está en:

```text
k8s/back.yml
```

Aplicar manualmente:

```bash
kubectl apply -f k8s/back.yml
kubectl get svc supermercado-service
```

Cuando el servicio tenga `EXTERNAL-IP` o DNS, abrir ese valor en el navegador.
Ahí se muestra el frontend del supermercado.

## Secrets necesarios en GitHub Actions

```text
AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
AWS_SESSION_TOKEN
ECR_REPOSITORY
EKS_CLUSTER
```

## Para mostrar en la presentación

1. Mostrar que el repositorio tiene un solo microservicio.
2. Mostrar el frontend cargando desde el LoadBalancer.
3. Probar el botón de ping.
4. Listar productos.
5. Hacer una compra y mostrar que baja el stock.
