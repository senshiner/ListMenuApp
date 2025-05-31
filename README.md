# Java GUI List Menu

##### Tugas Mata Kuliah Komputer Grafik
Aplikasi Java GUI untuk menampilkan berbagai project yang telah dibuat, seperti:
- Garis dan bentuk dasar (Grafik)
- Kurva Bézier (Kurva)
- Mode XOR (XOR Drawing)
- Diagram Pie (PieChart)

## 🚀 Cara Compile dan Jalankan

### Persyaratan:
- Java Development Kit (JDK) 8+
- Terminal (Linux/Mac) atau Command Prompt (Windows)

### Compile:
```bash
javac -d bin src/listmenu/*.java
```
### Run:
```bash
java -cp bin listmenu.ListMenuApp
```
#### 🧰 Run via NetBeans:
    1. Buat project Java baru (Java with Ant).
    2. Buat package listmenu lalu salin semua file dari src/listmenu/.
    3. Letakkan folder img/ di luar src/ (sejajar dengan build.xml).
    4. Jalankan file ListMenuApp.java.
    
## ➕ Add New Panel

1. Tambahkan file panel baru di `src/listmenu/`, misalnya `CustomPanel.java`
2. Buka `ListMenuApp.java`, lalu:
   - Di method `ListMenuApp()`, tambahkan:
     ```java
      projectSelector = new JComboBox<>(new String[]{
      "Line", "Kurva", "Grafik",...,"CustomPanel"});
     ```
   - Di method `updateProjectPanel()`, tambahkan:
     ```java
      else if ("CustomPanel".equals(selected)) {
      mainPanel.add(new CustomPanel(frameColor, fillColor), BorderLayout.CENTER);}
     ```
3. Compile dan jalankan ulang aplikasi.
