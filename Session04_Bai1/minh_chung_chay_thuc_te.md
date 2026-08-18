# MINH CHỨNG CHẠY THỰC TẾ HỆ THỐNG CẤU HÌNH ĐA MÔI TRƯỜNG (PROFILES)
**Ứng dụng:** AI Logistics Incident Reporter  
**Môi trường thử nghiệm:** Local OS Windows 11 / Java 17 / Spring Boot 4.1.0  
**Ngày thực hiện:** 18/08/2026  

---

## 1. TỔNG QUAN KỊCH BẢN KIỂM THỬ

Hệ thống được đóng gói thành tệp tin JAR `Session04_Bai1-0.0.1-SNAPSHOT.jar` và tiến hành khởi chạy thực tế dưới 2 profile khác nhau để chứng minh cơ chế nạp cấu hình động:
1. **Profile Local:** Kết nối mô hình LLM cục bộ `qwen2.5-coder:7b` qua Ollama (`http://localhost:11434`).
2. **Profile Cloud:** Kết nối mô hình LLM đám mây `google/gemini-2.5-flash` qua OpenRouter (`https://openrouter.ai/api/v1`).

---

## 2. KỊCH BẢN 1: KHỞI CHẠY VỚI PROFILE `LOCAL`

### 2.1. Lệnh thực thi (Execution Command)
```powershell
java -jar build/libs/Session04_Bai1-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

### 2.2. Log Console Khởi chạy thực tế (Actual Startup Console Logs)
```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.0)

2026-08-18T08:35:12.465+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : Starting Session04Bai1Application v0.0.1-SNAPSHOT using Java 17.0.12 with PID 33232 (D:\IT213\Session04\Session04_Bai1\Session04_Bai1\build\libs\Session04_Bai1-0.0.1-SNAPSHOT.jar started by GIA HUY in D:\IT213\Session04\Session04_Bai1\Session04_Bai1)
2026-08-18T08:35:12.470+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : The following 1 profile is active: "local"
2026-08-18T08:35:13.367+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8080 (http)
2026-08-18T08:35:13.380+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-08-18T08:35:13.380+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-08-18T08:35:13.413+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 875 ms
2026-08-18T08:35:13.764+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-18T08:35:13.776+07:00  INFO 33232 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : Started Session04Bai1Application in 1.764 seconds (process running for 2.264)
2026-08-18T08:35:43.429+07:00  INFO 33232 --- [logistics-incident-reporter] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-08-18T08:35:43.430+07:00  INFO 33232 --- [logistics-incident-reporter] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-08-18T08:35:43.431+07:00  INFO 33232 --- [logistics-incident-reporter] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 0 ms
```

> **Dấu hiệu nhận biết thành công trong Log:**  
> Dòng log số 12 thể hiện rõ:  
> `2026-08-18T08:35:12.470+07:00 INFO 33232 --- [logistics-incident-reporter] [ main] c.s.Session04Bai1Application : The following 1 profile is active: "local"`

### 2.3. Kết quả Gọi Endpoint Đối soát REST API (`GET /api/v1/incident/config`)
**Lệnh gọi API:**
```powershell
curl.exe -s http://localhost:8080/api/v1/incident/config
```

**Kết quả JSON phản hồi thực tế:**
```json
{
  "applicationName": "logistics-incident-reporter",
  "activeProfile": "local",
  "aiProvider": "Ollama (Local Infrastructure)",
  "activeModelName": "qwen2.5-coder:7b",
  "baseUrl": "http://localhost:11434",
  "timestamp": "2026-08-18T08:35:43.454656300",
  "status": "SUCCESS",
  "message": "Profile configuration verified successfully."
}
```

---

## 3. KỊCH BẢN 2: KHỞI CHẠY VỚI PROFILE `CLOUD`

### 3.1. Lệnh thực thi (Execution Command)
```powershell
java -jar build/libs/Session04_Bai1-0.0.1-SNAPSHOT.jar --spring.profiles.active=cloud
```

### 3.2. Log Console Khởi chạy thực tế (Actual Startup Console Logs)
```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.0)

2026-08-18T08:35:53.415+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : Starting Session04Bai1Application v0.0.1-SNAPSHOT using Java 17.0.12 with PID 16744 (D:\IT213\Session04\Session04_Bai1\Session04_Bai1\build\libs\Session04_Bai1-0.0.1-SNAPSHOT.jar started by GIA HUY in D:\IT213\Session04\Session04_Bai1\Session04_Bai1)
2026-08-18T08:35:53.421+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : The following 1 profile is active: "cloud"
2026-08-18T08:35:54.351+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8080 (http)
2026-08-18T08:35:54.364+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-08-18T08:35:54.364+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-08-18T08:35:54.418+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 932 ms
2026-08-18T08:35:54.851+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8080 (http) with context path '/'
2026-08-18T08:35:54.862+07:00  INFO 16744 --- [logistics-incident-reporter] [           main] c.s.Session04Bai1Application             : Started Session04Bai1Application in 1.931 seconds (process running for 2.413)
2026-08-18T08:36:07.400+07:00  INFO 16744 --- [logistics-incident-reporter] [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-08-18T08:36:07.400+07:00  INFO 16744 --- [logistics-incident-reporter] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-08-18T08:36:07.401+07:00  INFO 16744 --- [logistics-incident-reporter] [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
```

> **Dấu hiệu nhận biết thành công trong Log:**  
> Dòng log số 12 thể hiện rõ:  
> `2026-08-18T08:35:53.421+07:00 INFO 16744 --- [logistics-incident-reporter] [ main] c.s.Session04Bai1Application : The following 1 profile is active: "cloud"`

### 3.3. Kết quả Gọi Endpoint Đối soát REST API (`GET /api/v1/incident/config`)
**Lệnh gọi API:**
```powershell
curl.exe -s http://localhost:8080/api/v1/incident/config
```

**Kết quả JSON phản hồi thực tế:**
```json
{
  "applicationName": "logistics-incident-reporter",
  "activeProfile": "cloud",
  "aiProvider": "OpenRouter Aggregator (Cloud Infrastructure)",
  "activeModelName": "google/gemini-2.5-flash",
  "baseUrl": "https://openrouter.ai/api/v1",
  "timestamp": "2026-08-18T08:36:07.433108500",
  "status": "SUCCESS",
  "message": "Profile configuration verified successfully."
}
```

---

## 4. BẢNG DỐI SOÁT TỔNG HỢP (SO SÁNH KẾT QUẢ PROFILES)

| Tiêu chí | Profile `local` (Default/Chạy thử) | Profile `cloud` (Production/Cloud) | Trạng thái |
| :--- | :--- | :--- | :--- |
| **Active Profile** | `local` | `cloud` | ✅ Nhận diện đúng |
| **Mô hình AI Active** | `qwen2.5-coder:7b` | `google/gemini-2.5-flash` | ✅ Chuyển đổi tự động |
| **Hạ tầng / Provider** | Ollama (Local Infrastructure) | OpenRouter Aggregator (Cloud) | ✅ Nạp đúng properties |
| **Base URL Endpoint** | `http://localhost:11434` | `https://openrouter.ai/api/v1` | ✅ Khác biệt hoàn toàn |
| **Sửa đổi Mã Java** | **0 dòng (Zero Line)** | **0 dòng (Zero Line)** | ✅ Đạt yêu cầu |

---

## 5. KẾT LUẬN

Minh chứng chạy thực tế cho thấy hệ thống **AI Logistics Incident Reporter** đã triển khai thành công cấu hình đa môi trường (Spring Profiles). Ứng dụng tự động điều chỉnh toàn bộ hạ tầng kết nối AI và tham số mô hình tương ứng chỉ bằng cách thay đổi giá trị `--spring.profiles.active`, đáp ứng hoàn hảo 100% tất cả các yêu cầu đầu ra của bài toán.
