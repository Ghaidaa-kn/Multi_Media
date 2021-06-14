//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Interfaces;

import algo.JPEG_Compression;
import algo.LZW;
import algo.ShannonFano;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {
    public static final String extension = ".c";
    public VBox vBoxDirs;
    public VBox vBoxFiles;
    public Button buttonCompressRle;
    public Button buttonCompressHuffman;
    public Button buttonCompressAdaptive;
    public Button buttonCompressShannonFano;
    public Button buttonCompressRleFile;
    public Button buttonCompressHuffmanFile;
    public Button buttonCompressAdaptiveFile;
    public Button buttonCompressShannonFanoFile;
    public Button buttonDecompressRle;
    public Button buttonDecompressHuffman;
    public Button buttonDecompressAdaptive;
    public Button buttonDecompressShannonFano;
    public Button buttonCompressFiles;
    public Button buttonCompressDirs;
    public Label labelCompressionRatio;
    public Button buttonCompressLZWFile;
    public Button buttonDecompressLZW;
    public Button buttonCompressLZW;
    public Button buttonjpeg;
    public Button buttonjpeg1;


    public Controller() {
    }

    @FXML
    private void initialize() {
        this.init();
    }

    private void init() {

//        this.buttonCompressRle.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
//            this.setLoadingLabel();
//            RLE compression = new RLE();
//            File file = this.loadDir();
//            if (file != null) {
//                compression.compressDir(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
//                this.setCompressionRatio(compression.getCompressionRatio(file, new File(file.getAbsoluteFile() + ".amo")));
//            }
//
//        });
       /* this.buttonCompressHuffman.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            Huffman compression = new Huffman();
            File file = this.loadDir();
            if (file != null) {
                compression.DirectoryCompression(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".amo")));
            }

        });*/
//        this.buttonCompressAdaptive.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
//            this.setLoadingLabel();
//            Adaptive compression = new Adaptive();
//            File file = this.loadDir();
//            if (file != null) {
//                compression.DirectoryCompression(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
//                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".amo")));
//            }
//        });

        this.buttonCompressShannonFano.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            ShannonFano compression = new ShannonFano();
            File file = this.loadDir();
            if (file != null) {
                compression.DirectoryCompression(file.getAbsolutePath(), file.getAbsolutePath() + ".shf");
                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".shf")));
            }

        });
       /* this.buttonCompressRleFile.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            RLE compression = new RLE();
            File file = this.loadFile();
            if (file != null) {
                compression.compressSingleFile(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
                this.setCompressionRatio(compression.getCompressionRatio(file, new File(file.getAbsoluteFile() + ".amo")));
            }

        });
        this.buttonCompressHuffmanFile.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            Huffman compression = new Huffman();
            File file = this.loadFile();
            if (file != null) {
                compression.compressSingleFile(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".amo")));
            }

        });*/
//        this.buttonCompressAdaptiveFile.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
//            this.setLoadingLabel();
//            Adaptive compression = new Adaptive();
//            File file = this.loadFile();
//            if (file != null) {
//                compression.compressSingleFile(file.getAbsolutePath(), file.getAbsolutePath() + ".amo");
//                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".amo")));
//            }
//
//        });
        this.buttonCompressShannonFanoFile.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            ShannonFano compression = new ShannonFano();
            File file = this.loadFile();
            if (file != null) {
                try {
                    compression.compressSingleFile(file.getAbsolutePath(), file.getAbsolutePath() + ".shf");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".shf")));
            }

        });
        this.buttonDecompressShannonFano.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel2();
            ShannonFano compression = new ShannonFano();
            File file = this.loadFile();
            if (file != null) {
                    compression.decompression(file.getAbsolutePath(), file.getParent());
                //new File(file.getAbsoluteFile() +".dshf");
                this.setSuccessfulDecompression();
            }

        });

        this.buttonCompressLZWFile.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            LZW compression = new LZW();
            File file = this.loadFile();
            if (file != null) {
                try {
                    compression.LZW_Compress(file.getAbsolutePath(), file.getAbsolutePath() + ".lzw");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".lzw")));
            }

        });

//        this.buttonCompressLZW.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
//            this.setLoadingLabel();
//            LZW compression = new LZW();
//            File file = this.loadFile();
//            if (file != null) {
//                try {
//                    compression.DirectoryCompression(file.getAbsolutePath(), file.getAbsolutePath() + ".lzw");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".lzw")));
//            }
//        });



        this.buttonDecompressLZW.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel2();
            LZW decompression = new LZW();
            File file = this.loadFile();
            if (file != null) {
                try {
//                    File f = new File(file.getAbsoluteFile()+"dlzw");
//                    String s = f.getAbsolutePath().toString();
                    decompression.LZW_Decompress(file.getAbsolutePath() , new File(file.getPath()+"_dlzw").toString() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.setSuccessfulDecompression();
            }

        });





//


        this.buttonjpeg1.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.setLoadingLabel();
            JPEG_Compression compression = new JPEG_Compression();
            File file = this.loadFile();
            if (file != null) {
                try {
                    compression.compress(file.getAbsolutePath() , new File(file.getAbsoluteFile()+".jpeg").toString() );
                } catch (IOException e) {
                    e.printStackTrace();
                }
               // this.setCompressionRatio(compression.getComRatio(file, new File(file.getAbsoluteFile() + ".jpeg")));
                this.setSuccessfulDecompression();

            }

        });


        this.buttonCompressFiles.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.vBoxDirs.setVisible(false);
            this.vBoxFiles.setVisible(true);
        });
        this.buttonCompressDirs.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            this.vBoxDirs.setVisible(true);
            this.vBoxFiles.setVisible(false);
        });
    }

    private File loadDir() {
        DirectoryChooser fileDialog = new DirectoryChooser();
        fileDialog.setInitialDirectory(new File(System.getProperty("user.home")));
        fileDialog.setTitle("Select Directory to Compress");
        File selectedFile = fileDialog.showDialog((Window)null);
        return selectedFile == null ? null : selectedFile;
    }

    private File loadFile() {
        FileChooser fileDialog = new FileChooser();
        fileDialog.setInitialDirectory(new File(System.getProperty("user.home")));
        fileDialog.setSelectedExtensionFilter(new ExtensionFilter("Amo extension", new String[]{"*.c"}));
        fileDialog.setTitle("Select File to Compress");
        File selectedFile = fileDialog.showOpenDialog((Window)null);
        return selectedFile == null ? null : selectedFile;
    }

    private void setLoadingLabel() {
        this.labelCompressionRatio.setText("PLEAES WAIT...");
    }

    private void setLoadingLabel2() {
        this.labelCompressionRatio.setText("PLEASE WAIT...");
    }

    private void setCompressionRatio(double value) {
        this.labelCompressionRatio.setText("DONE AND THE RATIO IS : " + value + "  % ");
    }

    private void setSuccessfulDecompression() {
        this.labelCompressionRatio.setText("... DONE ...");
    }
}
