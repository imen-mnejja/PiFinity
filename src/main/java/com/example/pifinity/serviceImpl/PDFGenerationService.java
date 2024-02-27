package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Partner;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFGenerationService {

    private final PartnerServiceImpl partnerService;

    public PDFGenerationService(PartnerServiceImpl partnerService) {
        this.partnerService = partnerService;
    }

    public byte[] generatePDF() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument, PageSize.A4);

        List<Partner> partners = partnerService.findAllPartners();

        // Titre de la page
        Paragraph title = new Paragraph("Liste des partenaires")
                .setFont(PdfFontFactory.createFont())
                .setFontSize(18)
                .setFontColor(DeviceRgb.RED)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(title);

        for (Partner partner : partners) {
            // Informations sur le partenaire
            Div partnerDetails = new Div()
                    .add(new Paragraph("Nom: " + partner.getName()))
                    .add(new Paragraph("Domaine: " + partner.getDomain()))
                    .add(new Paragraph("Nombre de transactions: " + partner.getNbtransaction()))
                    .add(new Paragraph("Bénéfice net: " + partner.getBeneficenet()))
                    .add(new Paragraph("Chiffre d'affaires: " + partner.getChiffredaffaires()))
                    .add(new Paragraph("Charges fixes: " + partner.getChargesfixes()));

            // Informations sur les jointures
            if (partner.getAdvertising() != null) {
                partnerDetails.add(new Paragraph("Publicité: " + partner.getAdvertising().getDescription()));
            }
            if (partner.getBankAccount() != null) {
                partnerDetails.add(new Paragraph("Compte bancaire: RIB " + partner.getBankAccount().getRIB()));
            }
            // Ajoutez d'autres jointures et informations ici

            // Ajoutez les détails du partenaire à la page
            document.add(partnerDetails);

            // Ajoutez un saut de page entre chaque partenaire
            document.add(new AreaBreak());
        }

        document.close();
        return outputStream.toByteArray();
    }
}
