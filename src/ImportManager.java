import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ImportManager {
    File file;

    public ImportManager() {
        this.file = new File("src/JUMPS-data-example.xlsx");
        this.importSupplies();
        this.importComponents();
    }

    public boolean importSupplies () {
        try {
            System.out.println("I am importing the supplies of the system!!");
            System.out.println("");
            FileInputStream fis = new FileInputStream(this.file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Supplies");     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                if(row.getRowNum() == 0){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String name = cellIterator.next().getStringCellValue();
                    if (name.isEmpty()) {
                        break;
                    }
                    int quantityAvailable =(int) cellIterator.next().getNumericCellValue();
                    int quantityOriginal =  (int) cellIterator.next().getNumericCellValue();
                    String type = cellIterator.next().getStringCellValue();
                    String unit = cellIterator.next().getStringCellValue();
                    System.out.println(name + ", " + quantityAvailable + ", " + quantityOriginal + ", " + type + ", " + unit);
                }
            }
            wb.close();
            System.out.println("");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean importComponents () {
        try {
            System.out.println("I am importing the Components of the system!!");
            System.out.println("");
            FileInputStream fis = new FileInputStream(this.file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            //Sheet in excel file is labeled as Capabilities but they're the Components of the UAV
            XSSFSheet sheet = wb.getSheet("Capabilities");     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                if(row.getRowNum() == 0){
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    String type = cellIterator.next().getStringCellValue();
                    if (type.isEmpty()) {
                        break;
                    }
                    String id = cellIterator.next().getStringCellValue();
                    String name =  cellIterator.next().getStringCellValue();
                    String description = cellIterator.next().getStringCellValue();
                    String status = cellIterator.next().getStringCellValue();
                    System.out.println(name + " is a " + type + " with ID: " + id + " and is currently " + status + ".\n Here is a description of it: " + description);
                }
            }
            System.out.println("");
            wb.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}