package com.createpdf.pro.createpdf;


import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
   private Button button;
   private EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnPDF);
        txt  = findViewById(R.id.txtPDF);
        final File file = new File(getBaseContext().getExternalCacheDir().getPath()+"/"+"PDF1"+".pdf");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintedPdfDocument document = new PrintedPdfDocument(MainActivity.this, getPrintAttributes());


                PdfDocument.Page page = document.startPage(1);


                View content = txt;
                content.draw(page.getCanvas());


                document.finishPage(page);



                try {
                    file.createNewFile();
                    FileOutputStream outputstream =  new FileOutputStream(file);
                    document.writeTo(outputstream);
                    Toast.makeText(MainActivity.this, "PDF Created Successfully!",
                            Toast.LENGTH_LONG).show();
                    outputstream.flush();
                    outputstream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }


                document.close();
            }
        });
    }


    private PrintAttributes getPrintAttributes() {
        PrintAttributes.Builder builder = new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setResolution(new PrintAttributes.Resolution("res1","Resolution",50,50)).setMinMargins(new PrintAttributes.Margins(5, 5, 5, 5));
        PrintAttributes printAttributes = builder.build();
        return printAttributes;
    }



}
