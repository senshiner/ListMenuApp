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
## ➕ Add New Panel

1. Tambahkan file panel baru di `src/listmenu/`, misalnya `TestPanel.java`
2. Buka `ListMenuApp.java`, lalu:
   - Di method `initializeMenuPanel()`, tambahkan:
     ```java
     projectComboBox.addItem("Test");
     ```
   - Di method `initializeProjectPanel()`, tambahkan:
     ```java
     cardPanel.add(new TestPanel(), "Test");
     ```
3. Compile dan jalankan ulang aplikasi.
