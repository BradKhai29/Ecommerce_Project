package controller.uploadFile_support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import webpage_tools.WebPageEnum;

@MultipartConfig
@WebServlet(name = "FileUploadServlet", urlPatterns = {"/FileUploadServlet", "/upload"})
public class FileUploadServlet extends HttpServlet {
    private static final String rootURL = WebPageEnum.HOME.getURL();
    private static final String systemPathSeparator = File.separator;
    
    private static Part uploadPart = null;
    private static final StringBuffer ABSOLUTE_PATH_GENERATOR = new StringBuffer();
    private static String fileUploadAbsolutePath = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(rootURL);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(rootURL);
    }
    
    private static void resetAll()
    {
        uploadPart = null;
        ABSOLUTE_PATH_GENERATOR.delete(0, ABSOLUTE_PATH_GENERATOR.length());
        fileUploadAbsolutePath = "";
    }
    
    private static void removeBuildFolderInPath()
    {
        int removeBuildFolderPosition = ABSOLUTE_PATH_GENERATOR.lastIndexOf(systemPathSeparator.concat("build"));
        ABSOLUTE_PATH_GENERATOR.replace(removeBuildFolderPosition, removeBuildFolderPosition + 6, "");
    }
    
    private static void addUploadFolder(String folderName)
    {
        ABSOLUTE_PATH_GENERATOR.append(folderName);
    }
    
    
    private static void createFolder(String folderAbsolutePath)
    {
        File folder = new File(folderAbsolutePath);
        boolean folderNotExist = folder.exists();
        
        if (folderNotExist == false) {
            folder.mkdir();
            System.out.println("Create folder : [" + folder.getAbsolutePath()+ "] success");
        }
    }
    
    public static void init(HttpServletRequest request, Part fileUpload, String folderUploadName) throws IOException
    {
        System.out.println("Served at [FileUpload Servlet]");
        resetAll();
        
        //Set upload Part
        uploadPart = fileUpload;
        
        //Get the Current web app absolute path
        ServletContext currentWebApp = request.getServletContext();
        ABSOLUTE_PATH_GENERATOR.append(currentWebApp.getRealPath(""));
        
        //Move the path to current web folder of this web app
        removeBuildFolderInPath();
        //Add upload folder to the path
        addUploadFolder(folderUploadName);
        String folderUploadAbsolutePath = ABSOLUTE_PATH_GENERATOR.toString();      
        System.out.println("Current absolute Path: [" + folderUploadAbsolutePath + "]");
          
        //Create new Folder if not existed
        createFolder(folderUploadAbsolutePath);
        
        String fileName = uploadPart.getSubmittedFileName();
        System.out.println("File name = " + fileName);
        
        fileUploadAbsolutePath = ABSOLUTE_PATH_GENERATOR.append(systemPathSeparator).append(fileName).toString();
        System.out.println("Upload file absolute path = [" + fileUploadAbsolutePath + "]");
    }
    
    public static boolean processUpload()
    {
        boolean uploadSuccess = false;
        
        try {
            FileOutputStream outputStream = new FileOutputStream(fileUploadAbsolutePath);
            
            byte[] dataBytes = getContentFrom(uploadPart);
            //Write the content get from part to Disk
            outputStream.write(dataBytes);
            
            //After upload, flush and close the stream
            outputStream.flush();            
            outputStream.close();
            uploadSuccess = true;
            
            System.out.println("Upload file [" + uploadPart.getSubmittedFileName() + "] success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        resetAll();
        return uploadSuccess;
    }
    
    /**
     * This method will return the URL of the 
     * @param filePart the Part belong to uploaded file
     * @param uploadFolder the Folder to upload this file
     * @return 
     */
    public static String getFileURL(Part filePart, String uploadFolder)
    {
        StringBuffer URL = new StringBuffer(uploadFolder);
        URL.append(systemPathSeparator);
        URL.append(filePart.getSubmittedFileName());
        
        return URL.toString();
    }
    
    private static byte[] getContentFrom(Part contentPart) throws IOException
    {
        InputStream contentStream = contentPart.getInputStream();
        byte[] dataBytes = contentStream.readAllBytes();
        
        contentStream.close();
        return dataBytes;
    }
}
