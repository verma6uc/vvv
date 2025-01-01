# Yuvi

A web-based application built with Java Servlets, PostgreSQL, and modern web technologies.

## Technologies Used

- Java 17
- Jakarta Servlet 6.0
- PostgreSQL Database
- Gson for JSON processing
- Bootstrap for frontend styling
- JSTL for JSP support

## Project Structure

```
yuvi/
├── src/
│   ├── main/
│   │   ├── java/        # Java source files
│   │   ├── resources/   # Configuration files
│   │   └── webapp/      # Web resources (HTML, CSS, JS, JSP)
│   └── test/
│       └── java/        # Test files
├── pom.xml             # Maven configuration
├── CHANGELOG.md        # Project changes log
└── README.md          # Project documentation
```

## Prerequisites

- Java Development Kit (JDK) 17
- Apache Maven
- PostgreSQL Database Server
- Apache Tomcat 10.1.18 (Required for Jakarta EE 9+ support)

## Building the Project

```bash
mvn clean install
```

## Development Setup

1. Clone the repository
2. Import as Maven project in your IDE
3. Configure PostgreSQL connection (details will be in application.properties)
4. Run on Tomcat server

## Contributing

1. Create a feature branch
2. Commit your changes
3. Push to the branch
4. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
