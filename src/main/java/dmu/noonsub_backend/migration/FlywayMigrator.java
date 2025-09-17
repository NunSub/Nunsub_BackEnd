package dmu.noonsub_backend.migration;

import org.flywaydb.core.Flyway;

public class FlywayMigrator {
    public static void main(String[] args) {
        // GitHub Actions 워크플로우에서 주입해준 환경 변수를 읽어옵니다.
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // 환경 변수가 없으면 오류를 발생시키고 종료합니다.
        if (url == null || user == null || password == null) {
            System.err.println("Database environment variables (DB_URL, DB_USER, DB_PASSWORD) are not set.");
            System.exit(1);
        }

        System.out.println("Starting Flyway migration...");

        try {
            // Flyway 라이브러리를 사용해 마이그레이션을 실행합니다.
            Flyway flyway = Flyway.configure()
                    .dataSource(url, user, password)
                    .load();
            flyway.migrate();
            System.out.println("Flyway migration finished successfully.");
        } catch (Exception e) {
            System.err.println("Flyway migration failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}