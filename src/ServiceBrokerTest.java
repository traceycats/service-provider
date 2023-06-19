/**************************************************************************************
 * JUnit Test Class for Service Broker
 * ***********************************************************************************
 * Function:
 * Tests service broker to ensure correct service is being called
 *--------------------------------------------------------------------------------------
 *
 * @author David Newman
 * @version 04/21/2023 CMSC 355
 *************************************************************************************/
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceBrokerTest {

    private static final String TEST_FILE_PATH = "test_services.txt";
    private static final String TEST_FILE_CONTENT = "1,Service1\n2,Service2\n3,Service3\n";

    private File testFile;

    @BeforeEach
    public void setup() throws IOException {
        testFile = createTestFile(TEST_FILE_PATH, TEST_FILE_CONTENT);
    }

    @AfterEach
    public void cleanup() {
        deleteTestFile(testFile);
    }

    @Test
    public void testReadServicesFromFile() throws FileNotFoundException {
        ServiceBroker serviceBroker = new ServiceBroker();
        HashMap<Integer, String> serviceMap = serviceBroker.readServicesFromFile();

        assertEquals(3, serviceMap.size());
        assertEquals("DigitConverter", serviceMap.get(0));
        assertEquals("SkillsSearcher", serviceMap.get(1));
        assertEquals("Translator", serviceMap.get(2));
    }

    private File createTestFile(String filePath, String fileContent) throws IOException {
        File file = new File(filePath);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(fileContent);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return file;
    }

    private void deleteTestFile(File file) {
        if (file != null && file.exists()) {
            assertTrue(file.delete());
        }
    }
}