# HavaDurumum 

Kotlin ve Jetpack Compose kullanılarak geliştirilmiş bir Android hava durumu uygulamasıdır.

## Proje Hakkında

HavaDurumum, Türkiye'nin 81 ilinin güncel hava durumu bilgilerini görüntülemeyi sağlayan bir mobil uygulamadır. Kullanıcılar istedikleri şehri seçerek hava durumu bilgilerine ulaşabilir.

## Özellikler

- Türkiye'nin 81 ilini listeleme
- Şehir adına veya plaka koduna göre arama
- Seçilen şehrin güncel sıcaklığını görüntüleme
- Hissedilen sıcaklık bilgisi
- Nem oranı ve rüzgâr hızı
- Hava durumuna göre açıklama ve simge gösterimi
- İnternet üzerinden güncel hava durumu verilerini alma

## Kullanılan Teknolojiler

- **Kotlin:** Android uygulama geliştirme dili
- **Jetpack Compose:** Kullanıcı arayüzü
- **MVVM:** Uygulama mimarisi
- **Retrofit:** API istekleri
- **Gson:** JSON verilerini Kotlin nesnelerine dönüştürme
- **StateFlow:** Arayüz durumlarının yönetimi
- **Open-Meteo API:** Hava durumu verileri

## Proje Yapısı

- `model`: Veri modelleri
- `network`: API bağlantıları
- `repository`: Veri erişim katmanı
- `viewmodel`: Uygulama mantığı ve durum yönetimi
- `ui`: Kullanıcı arayüzü bileşenleri
- `utils`: Yardımcı fonksiyonlar

## Çalıştırma

1. Projeyi GitHub üzerinden indirin veya klonlayın.
2. Android Studio ile açın.
3. Gradle senkronizasyonunun tamamlanmasını bekleyin.
4. Bir Android emülatörü veya fiziksel cihaz seçin.
5. Run butonuna basarak uygulamayı başlatın.

**Not:** Güncel hava durumu verilerini görüntülemek için internet bağlantısı gereklidir.

## 👩‍💻 Geliştirici

Şeyda Kurbetoğlu
