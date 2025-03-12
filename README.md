# Photo Gallery Manager

Photo Gallery Manager to aplikacja internetowa umożliwiająca efektywne zarządzanie kolekcjami zdjęć, organizowanie ich poprzez metadane i tagi oraz analizowanie trendów w użyciu obrazów.

## Spis treści

- [Opis projektu](#opis-projektu)
- [Funkcjonalności](#funkcjonalności)
- [Technologie](#technologie)
- [Architektura systemu](#architektura-systemu)
- [Struktura folderów i plików](#struktura-folderów-i-plików)
- [API Endpoints](#api-endpoints)
- [Instalacja i uruchomienie](#instalacja-i-uruchomienie)
- [Role użytkowników](#role-użytkowników)
- [Struktura bazy danych](#struktura-bazy-danych)
- [Strategie skalowania](#strategie-skalowania)
- [Bezpieczeństwo](#bezpieczeństwo)
- [Licencja](#licencja)

## Opis projektu

Photo Gallery Manager to kompleksowe rozwiązanie zaprojektowane dla użytkowników, którzy potrzebują efektywnego systemu do zarządzania zbiorami obrazów. Aplikacja umożliwia wgrywanie, organizowanie i kategoryzowanie obrazów za pomocą metadanych i tagów. System zapewnia również wgląd w trendy związane z obrazami, pomagając użytkownikom analizować popularne tagi i kategorie w czasie.

Motywacją do stworzenia aplikacji jest rosnąca potrzeba ustrukturyzowanego i dostępnego zarządzania obrazami. Wiele osób zmaga się z utrzymaniem porządku w swoich kolekcjach obrazów, szczególnie profesjonaliści tacy jak fotografowie, projektanci i twórcy treści, ale również zwykli użytkownicy.

## Funkcjonalności

- **Zarządzanie użytkownikami**: Rejestracja, logowanie, resetowanie hasła, zarządzanie profilem
- **Zarządzanie obrazami**: Wgrywanie, organizowanie, tagowanie i wyszukiwanie obrazów
- **Galerie**: Tworzenie prywatnych i publicznych galerii
- **System tagowania**: Dodawanie i zarządzanie tagami
- **Komentarze i oceny**: Interakcja z obrazami poprzez komentarze i oceny
- **Analiza trendów**: Śledzenie popularności tagów, aktywności użytkowników i trendów wgrywania
- **Powiadomienia**: System powiadomień o nowych komentarzach, ocenach i trendach
- **Kontrola dostępu**: Różne poziomy dostępu dla różnych ról użytkowników
- **Przetwarzanie wsadowe**: Możliwość wykonywania operacji na wielu obrazach jednocześnie

## Technologie

- **Backend**: Java 17, Spring Boot 3.x, Spring Security, Spring Data JPA
- **Baza danych**: MySQL 8.x
- **ORM**: Hibernate
- **Dokumentacja API**: Swagger/OpenAPI
- **Autentykacja**: JWT (JSON Web Tokens)
- **Migracje bazy danych**: Flyway
- **Testy**: JUnit 5, Mockito
- **Konteneryzacja**: Docker, Docker Compose
- **Narzędzie budowania**: Maven

## Architektura systemu

System wykorzystuje architekturę warstwową:

1. **Warstwa kontrolerów (REST API)**: Obsługuje żądania HTTP i zwraca odpowiedzi
2. **Warstwa serwisów**: Zawiera logikę biznesową
3. **Warstwa repozytoriów**: Odpowiada za dostęp do bazy danych
4. **Warstwa encji**: Definiuje modele danych

### Diagram klas

[Patrz diagram klas w pliku class-diagram.md]

## Struktura folderów i plików

```
photo-gallery-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── photogallery/
│   │   │           ├── PhotoGalleryManagerApplication.java
│   │   │           ├── config/
│   │   │           │   ├── SecurityConfig.java
│   │   │           │   ├── JwtConfig.java
│   │   │           │   └── SwaggerConfig.java
│   │   │           ├── controller/
│   │   │           │   ├── UserController.java
│   │   │           │   ├── ImageController.java
│   │   │           │   ├── GalleryController.java
│   │   │           │   ├── TagController.java
│   │   │           │   ├── CommentController.java
│   │   │           │   ├── RatingController.java
│   │   │           │   ├── NotificationController.java
│   │   │           │   └── TrendAnalysisController.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   │   ├── UserRequest.java
│   │   │           │   │   ├── ImageRequest.java
│   │   │           │   │   ├── GalleryRequest.java
│   │   │           │   │   ├── TagRequest.java
│   │   │           │   │   ├── CommentRequest.java
│   │   │           │   │   ├── RatingRequest.java
│   │   │           │   │   └── TrendAnalysisRequest.java
│   │   │           │   └── response/
│   │   │           │       ├── UserResponse.java
│   │   │           │       ├── ImageResponse.java
│   │   │           │       ├── GalleryResponse.java
│   │   │           │       ├── TagResponse.java
│   │   │           │       ├── CommentResponse.java
│   │   │           │       ├── RatingResponse.java
│   │   │           │       ├── NotificationResponse.java
│   │   │           │       └── TrendAnalysisResponse.java
│   │   │           ├── entity/
│   │   │           │   ├── User.java
│   │   │           │   ├── UserRole.java
│   │   │           │   ├── Image.java
│   │   │           │   ├── Tag.java
│   │   │           │   ├── ImageTag.java
│   │   │           │   ├── Gallery.java
│   │   │           │   ├── GalleryImage.java
│   │   │           │   ├── Comment.java
│   │   │           │   ├── Rating.java
│   │   │           │   ├── Notification.java
│   │   │           │   ├── NotificationType.java
│   │   │           │   ├── TrendAnalysis.java
│   │   │           │   └── TrendType.java
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── ImageRepository.java
│   │   │           │   ├── TagRepository.java
│   │   │           │   ├── ImageTagRepository.java
│   │   │           │   ├── GalleryRepository.java
│   │   │           │   ├── GalleryImageRepository.java
│   │   │           │   ├── CommentRepository.java
│   │   │           │   ├── RatingRepository.java
│   │   │           │   ├── NotificationRepository.java
│   │   │           │   └── TrendAnalysisRepository.java
│   │   │           ├── service/
│   │   │           │   ├── UserService.java
│   │   │           │   ├── ImageService.java
│   │   │           │   ├── GalleryService.java
│   │   │           │   ├── TagService.java
│   │   │           │   ├── CommentService.java
│   │   │           │   ├── RatingService.java
│   │   │           │   ├── NotificationService.java
│   │   │           │   ├── TrendAnalysisService.java
│   │   │           │   ├── StorageService.java
│   │   │           │   └── SecurityService.java
│   │   │           ├── service/impl/
│   │   │           │   ├── UserServiceImpl.java
│   │   │           │   ├── ImageServiceImpl.java
│   │   │           │   ├── GalleryServiceImpl.java
│   │   │           │   ├── TagServiceImpl.java
│   │   │           │   ├── CommentServiceImpl.java
│   │   │           │   ├── RatingServiceImpl.java
│   │   │           │   ├── NotificationServiceImpl.java
│   │   │           │   ├── TrendAnalysisServiceImpl.java
│   │   │           │   ├── StorageServiceImpl.java
│   │   │           │   └── SecurityServiceImpl.java
│   │   │           ├── exception/
│   │   │           │   ├── GlobalExceptionHandler.java
│   │   │           │   ├── ResourceNotFoundException.java
│   │   │           │   ├── UnauthorizedException.java
│   │   │           │   ├── BadRequestException.java
│   │   │           │   └── StorageException.java
│   │   │           ├── security/
│   │   │           │   ├── JwtTokenProvider.java
│   │   │           │   ├── JwtAuthenticationFilter.java
│   │   │           │   ├── UserDetailsServiceImpl.java
│   │   │           │   └── JwtAuthenticationEntryPoint.java
│   │   │           ├── util/
│   │   │           │   ├── ImageUtil.java
│   │   │           │   ├── DateUtil.java
│   │   │           │   └── ValidationUtil.java
│   │   │           └── analytics/
│   │   │               ├── TagAnalytics.java
│   │   │               ├── UploadAnalytics.java
│   │   │               ├── UserActivityAnalytics.java
│   │   │               └── PopularityAnalytics.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       ├── db/
│   │       │   └── migration/
│   │       │       ├── V1__init_schema.sql
│   │       │       └── V2__add_indexes.sql
│   │       └── static/
│   │           └── uploads/
│   │               └── .gitkeep
│   └── test/
│       └── java/
│           └── com/
│               └── photogallery/
│                   ├── controller/
│                   │   ├── UserControllerTest.java
│                   │   ├── ImageControllerTest.java
│                   │   └── ...
│                   ├── service/
│                   │   ├── UserServiceTest.java
│                   │   ├── ImageServiceTest.java
│                   │   └── ...
│                   └── repository/
│                       ├── UserRepositoryTest.java
│                       ├── ImageRepositoryTest.java
│                       └── ...
├── pom.xml
├── .gitignore
├── Dockerfile
├── docker-compose.yml
└── README.md
```

Struktura projektu jest zorganizowana zgodnie z najlepszymi praktykami dla aplikacji Spring Boot:

- **config/**: Zawiera klasy konfiguracyjne dla Spring Security, JWT i Swagger
- **controller/**: Zawiera kontrolery REST API dla wszystkich funkcjonalności
- **dto/**: Obiekty transferu danych (Data Transfer Objects) używane do komunikacji z klientem
- **entity/**: Encje JPA reprezentujące tabele w bazie danych
- **repository/**: Interfejsy do komunikacji z bazą danych, wykorzystujące Spring Data JPA
- **service/**: Interfejsy usługowe definiujące logikę biznesową
- **service/impl/**: Implementacje interfejsów usługowych
- **exception/**: Niestandardowe klasy wyjątków i globalny handler wyjątków
- **security/**: Klasy związane z zabezpieczeniami i autentykacją JWT
- **util/**: Klasy narzędziowe pomocne w różnych częściach aplikacji
- **analytics/**: Klasy odpowiedzialne za analizę trendów i generowanie raportów
- **resources/**: Pliki konfiguracyjne, migracje bazy danych i zasoby statyczne

## API Endpoints

### Użytkownicy

- `POST /api/auth/register` - Rejestracja nowego użytkownika
- `POST /api/auth/login` - Logowanie użytkownika
- `POST /api/auth/refresh` - Odświeżenie tokenu JWT
- `GET /api/users/me` - Pobierz dane zalogowanego użytkownika
- `PUT /api/users/me` - Aktualizuj dane użytkownika
- `PUT /api/users/me/password` - Zmień hasło
- `DELETE /api/users/me` - Usuń konto

### Obrazy

- `POST /api/images` - Wgraj nowy obraz
- `GET /api/images` - Pobierz listę obrazów (z filtrowaniem)
- `GET /api/images/{id}` - Pobierz szczegóły obrazu
- `PUT /api/images/{id}` - Aktualizuj metadane obrazu
- `DELETE /api/images/{id}` - Usuń obraz
- `PUT /api/images/{id}/visibility` - Zmień widoczność obrazu
- `POST /api/images/batch` - Przetwarzanie wsadowe obrazów

### Tagi

- `POST /api/tags` - Utwórz nowy tag
- `GET /api/tags` - Pobierz listę tagów
- `PUT /api/tags/{id}` - Aktualizuj tag
- `DELETE /api/tags/{id}` - Usuń tag
- `POST /api/images/{imageId}/tags/{tagId}` - Dodaj tag do obrazu
- `DELETE /api/images/{imageId}/tags/{tagId}` - Usuń tag z obrazu

### Galerie

- `POST /api/galleries` - Utwórz nową galerię
- `GET /api/galleries` - Pobierz listę galerii
- `GET /api/galleries/{id}` - Pobierz szczegóły galerii
- `PUT /api/galleries/{id}` - Aktualizuj galerię
- `DELETE /api/galleries/{id}` - Usuń galerię
- `PUT /api/galleries/{id}/visibility` - Zmień widoczność galerii
- `POST /api/galleries/{galleryId}/images/{imageId}` - Dodaj obraz do galerii
- `DELETE /api/galleries/{galleryId}/images/{imageId}` - Usuń obraz z galerii

### Komentarze

- `POST /api/images/{imageId}/comments` - Dodaj komentarz
- `GET /api/images/{imageId}/comments` - Pobierz komentarze
- `PUT /api/comments/{id}` - Edytuj komentarz
- `DELETE /api/comments/{id}` - Usuń komentarz

### Oceny

- `POST /api/images/{imageId}/ratings` - Oceń obraz
- `PUT /api/ratings/{id}` - Aktualizuj ocenę
- `DELETE /api/ratings/{id}` - Usuń ocenę

### Powiadomienia

- `GET /api/notifications` - Pobierz powiadomienia
- `PUT /api/notifications/{id}/read` - Oznacz jako przeczytane
- `DELETE /api/notifications/{id}` - Usuń powiadomienie

### Analiza trendów

- `GET /api/trends/tags` - Pobierz trendy tagów
- `GET /api/trends/uploads` - Pobierz trendy wgrywania
- `GET /api/trends/activity` - Pobierz raport aktywności użytkowników
- `GET /api/trends/popularity` - Pobierz mapę cieplną popularności

## Instalacja i uruchomienie

### Wymagania wstępne

- Java 17 lub nowsza
- Maven 3.6 lub nowszy
- MySQL 8.x
- Docker i Docker Compose (opcjonalnie)

### Konfiguracja lokalna

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/your-username/photo-gallery-manager.git
   cd photo-gallery-manager
   ```

2. Skonfiguruj bazę danych MySQL:
   ```bash
   mysql -u root -p
   CREATE DATABASE photo_gallery;
   CREATE USER 'gallery_user'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON photo_gallery.* TO 'gallery_user'@'localhost';
   FLUSH PRIVILEGES;
   EXIT;
   ```

3. Dostosuj plik `application-dev.properties` do swoich potrzeb:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/photo_gallery
   spring.datasource.username=gallery_user
   spring.datasource.password=password
   ```

4. Zbuduj aplikację:
   ```bash
   mvn clean package
   ```

5. Uruchom aplikację:
   ```bash
   java -jar target/photo-gallery-manager.jar --spring.profiles.active=dev
   ```

### Uruchomienie z Docker Compose

1. Zbuduj obrazy i uruchom kontenery:
   ```bash
   docker-compose up -d
   ```

2. Aplikacja będzie dostępna pod adresem: http://localhost:8080

## Role użytkowników

### Role biznesowe

1. **Administrator** - Zarządza kontami użytkowników, ustawieniami systemu i ogólną konserwacją platformy.
2. **Zwykły użytkownik** - Wgrywa obrazy, dodaje tagi i metadane oraz wyświetla analizy trendów.
3. **Gość** - Przegląda publiczne galerie obrazów bez możliwości edycji.
4. **Moderator treści** - Przegląda wgrane obrazy, zarządza nieodpowiednią treścią i zapewnia zgodność z polityką platformy.

### Role techniczne

1. **Deweloper systemu** - Utrzymuje i aktualizuje kod aplikacji.
2. **Administrator bazy danych** - Zarządza bazą danych MySQL.
3. **Projektant UI/UX** - Pracuje nad elementami interfejsu użytkownika.
4. **Inżynier DevOps** - Nadzoruje wdrażanie, konserwację serwerów.

## Struktura bazy danych

Główne tabele w bazie danych:

- `users` - Dane użytkowników
- `images` - Metadane obrazów
- `tags` - Informacje o tagach
- `image_tags` - Relacje między obrazami a tagami
- `galleries` - Informacje o galeriach
- `gallery_images` - Relacje między galeriami a obrazami
- `comments` - Komentarze do obrazów
- `ratings` - Oceny obrazów
- `notifications` - Powiadomienia dla użytkowników
- `trend_analyses` - Wyniki analiz trendów

## Strategie skalowania

- **Skalowanie poziome**: Możliwość uruchomienia wielu instancji aplikacji za load balancerem
- **Cache'owanie**: Wykorzystanie Redis do cache'owania często używanych danych
- **Optymalizacja bazy danych**: Indeksy, sharding i inne techniki optymalizacji
- **Pamięć masowa obrazów**: Wykorzystanie systemu plików, Amazon S3 lub podobnego rozwiązania do przechowywania obrazów
- **CDN**: Integracja z CDN dla szybszego dostarczania obrazów

## Bezpieczeństwo

- **Autentykacja JWT**: Bezpieczne tokeny z czasem wygaśnięcia
- **Hashowanie haseł**: Wykorzystanie BCrypt do bezpiecznego przechowywania haseł
- **Autoryzacja oparta na rolach**: Kontrola dostępu do funkcjonalności na podstawie ról
- **Walidacja danych wejściowych**: Zapobieganie atakom injection
- **HTTPS**: Szyfrowanie komunikacji
- **Limity szybkości**: Ochrona przed atakami DoS

## Licencja

Ten projekt jest licencjonowany na warunkach licencji MIT. Zobacz plik LICENSE aby uzyskać więcej informacji.
