<div id="top"></div>

<br />
<div align="center">
  <a href="https://github.com/IvanBarLem/ASI">
    <img src="https://bowiebearsnews.com/wp-content/uploads/2018/11/TRavel.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">ASI - Análisis de Sistemas de Información</h3>

  <p align="center">
    🗺️ Desarrollo de la agencia de viajes
    <br />
    <a href="https://ivanbarlem.github.io/ASI/es/udc/asiproject/backend/services/package-summary.html"><strong>Explora la documentación »</strong></a>
  </p>
</div>

## 🚧 Tecnologías

Esta sección tiene como objetivo mostrar la lista de tecnologías empleadas para implementar, probar y ejecutar la aplicación.

-   [Docker](https://www.docker.com/)
-   [Hibernate](https://hibernate.org/)
-   [MySQL](https://www.mysql.com/)
-   [H2](https://www.h2database.com/)
-   [Java SE](https://www.oracle.com/java/)
-   [Maven](https://maven.apache.org/)
-   [Node](https://nodejs.org/)
-   [React](https://es.reactjs.org/)
-   [Material-UI](https://mui.com/)
-   [Recharts](https://recharts.org/)
-   [Github Actions](https://github.com/features/actions)

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 💻 Estructura

### 🧱 Backend

El [backend](backend) es la parte de la aplicación que procesa toda la lógica, también conocida como el lado del servidor. Esta se compone de un servidor y una base de datos a través del cual se recibe, procesa y envía la información solicitada por el usuario.

```
📦backend
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java.es.udc.asiproject
 ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┗ 📂security
 ┃ ┃ ┃ ┣ 📂persistence
 ┃ ┃ ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┃ ┗ 📂model
 ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┣ 📂exceptions
 ┃ ┃ ┃ ┃ ┗ 📂impl
 ┃ ┃ ┃ ┗ 📂utils
 ┃ ┃ ┗ 📂resources
 ┃ ┗ 📂test
 ┃   ┣ 📂java.es.udc.asiproject.backend.services
 ┃   ┗ 📂resources
 ┗ 📜pom.xml
```

Dentro del directorio [backend](backend) se encuentran los siguientes directorios:

-   `controller` como punto de acceso a los distintos servicios.
-   `service` que contiene los servicios locales de la aplicación.
-   `persistence` que se encarga de la comunicación con la base de datos.
-   `test` que contiene las pruebas de la aplicación.

### 🏛️ Frontend

El [frontend](frontend) es la parte de la aplicación que interactúa con los usuarios, también conocida como el lado del cliente. Básicamente, esto es lo que se ve al acceder al sitio web, así como los elementos que permiten la navegación dentro de la web, creando con ello la experiencia de usuario.

```
📦frontend
 ┗ 📂src
   ┣ 📂backend
   ┣ 📂i18n
   ┃ ┗ 📂messages
   ┣ 📂modules
   ┃ ┣ 📂agents
   ┃ ┣ 📂app
   ┃ ┣ 📂common
   ┃ ┣ 📂packs
   ┃ ┣ 📂products
   ┃ ┣ 📂products-stats
   ┃ ┣ 📂sales
   ┃ ┗ 📂users
   ┣ 📂polyfills
   ┗ 📂store
```

En la carpeta `src` se encuentra todo el código del [frontend](frontend) cuyas carpetas más importantes son:

-   `backend` para la comunicación con la API.
-   `i18n` para la internacionalización.
-   `modules` para los distintos módulos de la aplicación.

Cabe destacar que cada directorio dentro de `modules` es un módulo de la aplicación que dentro tiene un directorio components con todos los componentes de dicho módulo.

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 🔌 Instalación y uso

### 🧱 Backend

Antes de ejecutar la aplicación, el primer paso será crear la base de datos. Para ello simplemente tendrá que ejecutar el comando `docker-compose up` desde el directorio [backend](backend), donde se encuentra el fichero [docker-compose.yml](backend/docker-compose.yml). Si le agrega el parámetro `-d` [Docker](https://www.docker.com/) ejecutará los contenedores en segundo plano:

```
docker-compose up -d
```

Para parar y borrar los contenedores generados tendrá que ejecutar el siguiente comando:

```
docker-compose down --volumes
```

La autogeneración de las tablas necesarias por la aplicación está controlado por [Hibernate](https://hibernate.org/) a través del fichero de configuración [application-dev.yml](backend/src/main/resources/application-dev.yml).

El siguiente comando instalará los paquetes necesarios para la ejecución del [backend](backend):

```
mvn install
```

Esto únicamente será necesario realizarlo la primera vez antes de ejecutar la aplicación.

El siguiente comando sirve para levantar el [backend](backend) en el [localhost:8080](localhost:8080):

```
mvn spring-boot:run
```

### 🏗️ Pruebas

Las pruebas de la aplicación se pueden ejecutar con el siguiente comando:

```
mvn test
```

Además, se ha configurado un proceso de CI (integración continua), una práctica que permite a los desarrolladores envíar los cambios a un repositorio compartido, como GitHub, para que se creen y ejecuten automáticamente las pruebas, a fin de identificar inmediatamente cualquier error. Para ello se han empleado las [Github Actions](https://github.com/features/actions) configuradas a través del fichero [main.yml](.gitgub/workflows/main.yml).

### 🏛️ Frontend

YARN es un gestor dependencias de JavaScript. Con el siguiente comando podrá instalar los cambios necesarios para la ejecución del [frontend](frontend)

```
yarn
```

El siguiente comando sirve para levantar el [frontend](frontend) en el [localhost:3000](localhost:3000):

```
yarn start
```

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 🛠️ Gitflow

Gitflow define un modelo estricto de ramificación diseñado en torno a los lanzamientos de la aplicación, siendo este ideal para aquellos que lleven una planificación de entregas iterativas. Este flujo de trabajo permite la paralelización del desarrollo mediante ramas independientes, tanto para la preparación, mantenimiento y publicación de versiones como para la corrección de errores en cualquier momento. La funcionalidad y uso de cada rama se detalla a continuación.

-   🏹 main: es la rama principal que contiene cada una de las versiones estables de la aplicación que están destinadas para que puedan ser incluidas en producción.
-   🧰 develop: esta rama contiene todas las funcionalidades del proyecto y en ella se incluirán las nuevas funcionalidades que se desarrollen para la siguiente versión.
-   ⚙️ feature: las ramas de características están destinadas a contener _commits_ que representen una funcionalidad determinada de la aplicación y, al completarse dicha funcionalidad, está es incluida en la rama de desarrollo.
-   🧲 release: las ramas de lanzamiento contienen todas las versiones finales de un producto destinadas a ser incluidas en producción, siendo este un paso previo y preparatorio. En ella se incluyen todas las funcionalidades de la rama de desarrollo y se arregla cualquier error que contenga antes de entrar en producción.
-   🧹 hotfixes: estas ramas están destinadas a ser utilizadas para aplicar arreglos directamente sobre la rama principal cuando se encuentren errores graves en una versión de producción.

<p align="right">(<a href="#top">volver arriba</a>)</p>

## 👥 Autores

👤 David García Gondell: david.ggondell@udc.es

👤 Diego Ramil López: diego.ramil.lopez@udc.es

👤 Iván Barrientos Lema: ivan.barrientos.lema@udc.es

👤 Jaime Cabero Creus: jaime.cabero@udc.es

👤 Laura Ben Artiles: l.ben@udc.es

<p align="right">(<a href="#top">volver arriba</a>)</p>
