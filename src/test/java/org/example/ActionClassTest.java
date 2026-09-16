package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ActionClassTest {

    private static final String USER_HOME_PROPERTY = "user.home";
    private static final String ORIGINAL_USER_HOME = System.getProperty(USER_HOME_PROPERTY);

    private static Path testHomeDir;
    private static ActionClass actionClass;

    @BeforeAll
    static void setUp() throws IOException {
        testHomeDir = new File("target").toPath().resolve("test-home");
        if (!testHomeDir.toFile().mkdirs() && !testHomeDir.toFile().isDirectory()) {
            throw new IOException("Could not create test home directory: " + testHomeDir);
        }
        System.setProperty(USER_HOME_PROPERTY, testHomeDir.toString());
        actionClass = new ActionClass();
    }

    @AfterAll
    static void tearDown() {
        System.setProperty(USER_HOME_PROPERTY, ORIGINAL_USER_HOME);
    }

    @BeforeEach
    void cleanHome() {
        File[] pdfs = findPdfsInHome();
        for (File pdf : pdfs) {
            pdf.delete();
        }
    }

    @Test
    void convertImgToPdf_createsPdfForImage(@TempDir Path tempDir) throws Exception {
        File imageFile = createPng(tempDir.resolve("portrait.png"), 400, 300);

        actionClass.convertImgToPdf(imageFile);

        File[] createdPdfs = findPdfsInHome();
        assertEquals(1, createdPdfs.length, "Expected exactly one PDF to be created");
        assertTrue(createdPdfs[0].length() > 0);
    }

    @Test
    void convertImgsToPdfSeperate_createsOnePdfPerImage(@TempDir Path tempDir) throws Exception {
        File[] imageFiles = {
                createPng(tempDir.resolve("landscape.png"), 400, 300),
                createPng(tempDir.resolve("portrait.png"), 300, 400)
        };

        actionClass.convertImgsToPdfSeperate(imageFiles);

        File[] createdPdfs = findPdfsInHome();
        assertEquals(2, createdPdfs.length, "Expected one PDF per image");
        for (File pdf : createdPdfs) {
            assertTrue(pdf.length() > 0);
        }
    }

    @Test
    void convertHeicToJpeg_throwsForNonHeicFile(@TempDir Path tempDir) throws IOException {
        File notHeic = createPng(tempDir.resolve("fake.heic"), 100, 100);

        assertThrows(RuntimeException.class, () -> actionClass.convertHeicToJpeg(notHeic));
    }

    private File createPng(Path path, int width, int height) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, (x * 255 / width) << 16 | (y * 255 / height) << 8 | 128);
            }
        }
        File file = path.toFile();
        if (!ImageIO.write(image, "png", file)) {
            throw new IOException("No PNG writer available");
        }
        return file;
    }

    private File[] findPdfsInHome() {
        File[] pdfs = testHomeDir.toFile().listFiles((dir, name) -> name.startsWith("img_to_PDF") && name.endsWith(".pdf"));
        return pdfs != null ? pdfs : new File[0];
    }
}