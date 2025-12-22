# Curium

**Curium** is a comprehensive **School Management Software** developed by Ideoholic, designed to revolutionize educational administration. It centralizes and automates diverse school operations—class progress, exams, fee collection, notifications—all in a seamless, paperless setup. Curium is built for accuracy, efficiency, and modern schools.  
👉 Learn more at [ideoholic.com/curium](https://ideoholic.com/curium/)

---

## 🚀 Features

- **User-Friendly & Web-Based**: No installation hassles, start in under 10 minutes  
- **24/7 Support**: Round-the-clock assistance  
- **Data Import Tools**: Smooth migration from legacy systems  
- **Universal Usage**: Schools, colleges, and coaching institutes  
- **Affordable Pricing**: Transparent and cost-effective  
- **Custom & Graphical Reports**: Tailored visual insights  
- **Messaging, Alerts & Reminders**: Automated SMS/email notifications  
- **Smart Attendance**: Biometric and card-based integration  
- **Exam Management**: Reports, results, hall tickets  
- **Hosting Flexibility**: Cloud or on-premise with custom URL  
- **Security & Access Control**: Role-based privileges and secure logins  
- **Eco-Friendly**: Paperless, cloud-first system  

---

## 💡 Why Choose Curium?

1. **Seamless Automation** – Replace manual tasks with efficiency  
2. **Enhanced Collaboration** – Connects parents, teachers, and students  
3. **Data-Driven Insights** – Advanced analytics and dashboards  
4. **Scalable & Secure** – Supports institutions of all sizes  

---

## ⚙️ Getting Started

### Prerequisites
- Java 11+  
- Maven 3.6+  
- MySQL 8+ (or compatible database)  
- Git  

### Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/musaibasrar/Curium.git
   cd Curium

2. **Configure the database**
    Create a MySQL database (e.g., curium_db)
    Update application.properties with your DB credentials

3. **Build the project**
    mvn clean install

4. **Run the application**
    mvn spring-boot:run
    Access it at: http://localhost:8080

### Web Interface (JSP)
The application uses JavaServer Pages (JSP) to display the user interface.

*   **Folder Location**: All screen files are located under the resources in `/WEB-INF/jsp/`.
*   **File Extension**: The system automatically adds `.jsp` to the end of view names that are returned from a Controller.

## 📖 Usage

1. Login with admin credentials
2. Add classes, sections, students, and teachers
3. Configure exams and generate results
4. Manage fee collection and payment tracking
5. Explore parent–teacher–student communication features
6. Generate hall tickets, ID cards, certificates, and reports


## 📦 Modules

1. Students – Enrollment, promotions
2. Fees – Collection, concessions, receipts
3. Attendance – Student & staff tracking
4. Exams – Setup, marks, hall tickets
5. Timetable – Classes & teachers
6. Identity – ID card generation
7. Finance & Stock – Vouchers, suppliers, stock control
8. Mess & Payroll – Mess cards, payroll processing
9. Library & Notifications – Library and SMS alerts
10. Multi-Branch – Branch-level reporting
11. Certificates & Reports – Progress, admission abstracts


## 🤝 Contributing

  1. Fork the repository
  2. Create a feature branch
    git checkout -b feature/my-feature
  3. Commit changes
     git commit -m "Add my new feature"
  4. Push to branch
     git push origin feature/my-feature
  5. Open a Pull Request

## 📬 Support & Contact

Curium is maintained by Ideoholic
For inquiries, demos, or support, visit: ideoholic.com
