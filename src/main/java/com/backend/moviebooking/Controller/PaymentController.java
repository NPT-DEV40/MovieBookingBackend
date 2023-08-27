package com.backend.moviebooking.Controller;

import com.backend.moviebooking.Dtos.PaymentDto;
import com.backend.moviebooking.Service.Impl.VNPService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private VNPService paymentVNP;

    @GetMapping("/submitOrder")
    public ResponseEntity<?> submitOrder(HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        System.out.println(baseUrl);
        String vnpayUrl = paymentVNP.createOrder(200000, "test", baseUrl);
        return ResponseEntity.ok().body(vnpayUrl);
    }

    @GetMapping("/resultOrder")
    public String GetMapping(HttpServletRequest request, Model model) {
        int paymentStatus = paymentVNP.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        String resultReceive = paymentStatus == 1 ? "Success" : "Fail";

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setStatus(resultReceive);
        paymentDto.setOrderInfo(orderInfo);
        paymentDto.setTotalPrice(totalPrice);
        paymentDto.setPaymentTime(paymentTime);
        paymentDto.setTransactionId(transactionId);

        // Redirect to google
        // return "redirect:https://www.google.com";
        return "redirect:https://www.google.com";
    }
}