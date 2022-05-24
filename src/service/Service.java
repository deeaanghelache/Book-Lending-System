package service;

import java.io.IOException;
import java.util.Scanner;

public interface Service {
    void menu(Scanner scanner, AdminService admin, String username, AuditService audit) throws IOException;
}
