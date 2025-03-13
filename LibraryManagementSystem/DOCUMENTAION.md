
# 📚 Library Management System API Documentation

## 🚀 Getting Started

### **Prerequisites**
Ensure you have the following installed:
- **Java 11 or higher**
- **Maven** for building the project
- **MySQL** database server running on your local machine or an accessible host
- **Spring Boot** dependencies for the project
- **Postman** or any HTTP client to test the API

## Setting Up MySQL

1. **Install MySQL**: Make sure you have MySQL installed and running on your local machine.
    - You can download MySQL from [here](https://dev.mysql.com/downloads/).

2. **Create a database**:
   After installing MySQL, create a database for the Library Management System:
   ```sql
   CREATE DATABASE library_management;

### **Installation & Running the Application**

1. **Clone the Repository**
   ```bash
   git clone https://github.com/hussien1236/LibraryManagementSystem.git
   cd library-management
   ```

2. **Configure the Database**  
   Update the `application.properties` file with your database details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_management
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

   ```

3. **Build & Run the Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The API will be available at:
   ```
   http://localhost:8080
   ```

---

## 📌 API Endpoints

### **Books**
| Method | Endpoint | Description |  
|--------|---------|-------------|  
| `POST` | `/books` | Add a new book |  
| `GET` | `/books/{id}` | Retrieve a book by ID |  
| `PUT` | `/books/{id}` | Update book details |  
| `DELETE` | `/books/{id}` | Delete a book |  

#### **Example: Add a Book**
**Request:**
```json
POST /api/books
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "year": 2008,
  "isbn": "9780132350884"
}
```
**Response:**
```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "year": 2008,
  "isbn": "9780132350884"
}
```

---

### **Borrowing**
| Method | Endpoint | Description |  
|--------|---------|-------------|  
| `POST` | `/api/borrow/{bookId}/patron/{patronId}` | Borrow a book |  
| `PUT`  | `/api/borrow/{bookId}/patron/{patronId}/return`  | Return a book   |  

#### **Example: Borrow a Book**
**Request:**
```http
POST /api/borrow/1/patron/1
```
**Response:**
```json
{
  "book": {
    "title": "Clean Code"
  },
  "patron": {
    "name": "John Doe"
  }
}
```

---
### **Patron**

| Method | Endpoint                          | Description        |  
|--------|-----------------------------------|--------------------|  
| `GET`  | `/api/patrons`                    | Get all patrons    |  
| `GET`  | `/api/patrons/{id}`               | Get patron by ID   |  
| `POST` | `/api/patrons`                    | Create a new patron|  
| `PUT`  | `/api/patrons/{id}`               | Update patron by ID|  
| `DELETE`| `/api/patrons/{id}`              | Delete patron by ID|  

#### **Example: Add a Book**
**Request:**
```json
POST /api/patrons
Content-Type: application/json

{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "phoneNb": "009612345"
}
```
**Response:**
```json
{
   "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "phoneNb": "009612345"
}
```

---
## 🛠 Running Tests
To run tests, use:
```bash
mvn test
```

