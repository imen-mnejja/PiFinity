package com.example.pifinity.serviceImpl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.stereotype.Service;
///import com.google.zxing.client.MatrixToImageWriter;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service; // Importez cette classe

@Service // Ajoutez cette annotation pour indiquer que c'est un service géré par Spring
public class QRCodeService {
    public byte[] generateQRCode(String text) throws IOException {
        ByteArrayOutputStream outputStream = QRCode.from(text).to(ImageType.PNG).stream();
        return outputStream.toByteArray();
    }
}

