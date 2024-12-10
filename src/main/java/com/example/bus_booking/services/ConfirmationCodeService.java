package com.example.bus_booking.services;

import com.example.bus_booking.entities.Client;
import com.example.bus_booking.entities.ConfirmationCode;
import com.example.bus_booking.repositories.ConfirmationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationCodeService {
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final ClientService clientService;
    private final EmailService emailService;

    public ConfirmationCode createAndSendCode(Long clientId) {
        Client client = clientService.getClientById(clientId);
        String generatedCode = generateRandomCode();
        ConfirmationCode confirmationCode = new ConfirmationCode();
        confirmationCode.setClient(client);
        confirmationCode.setCode(generatedCode);
        confirmationCode.setCreatedTime(LocalDateTime.now());
        confirmationCode.setExpiredTime(LocalDateTime.now().plusMinutes(10));
        confirmationCode.setIsUsed(false);
        confirmationCode = confirmationCodeRepository.save(confirmationCode);

        emailService.sendEmail(client.getEmail(), "Код подтверждения", "Ваш код подтверждения:" + generatedCode);
        return confirmationCode;
    }

    public boolean validateCode(String code, Long clientId) {
        ConfirmationCode confirmationCode = confirmationCodeRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Неверный код подтверждения"));
        if (!confirmationCode.getClient().getId().equals(clientId)) {
            throw new IllegalArgumentException("Код не принадлежит указанному клиенту");
        }
        if (confirmationCode.getExpiredTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Код истёк");
        }
        if (confirmationCode.getIsUsed()) {
            throw new IllegalArgumentException("Код уже использован");
        }

        confirmationCode.setIsUsed(true);
        confirmationCodeRepository.save(confirmationCode);
        return true;
    }

    private String generateRandomCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void cleanUpOldCodes() {
        LocalDateTime now = LocalDateTime.now();
        confirmationCodeRepository.findAll().forEach(code -> {
            if (code.getExpiredTime().isBefore(now) || code.getIsUsed()) {
                confirmationCodeRepository.delete(code);
            }
        });
    }
}
