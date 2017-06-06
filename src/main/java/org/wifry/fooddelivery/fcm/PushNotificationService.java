package org.wifry.fooddelivery.fcm;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.wifry.fooddelivery.fcm.model.Push;
import org.wifry.fooddelivery.filters.HeaderRequestInterceptor;

/**
 * <p>
 * <b> Push Notification Service </b>
 * </p>
 * <p>
 * <p>
 * Service send message to FCM
 * </p>
 */

@Service
public class PushNotificationService {

    @Value("${my.fcm.key}")
    private String fcmKey;

    private static final String FCM_API = "https://fcm.googleapis.com/fcm/send";

    /**
     * @param push
     * @return
     */

    public FirebaseResponse sendNotification(Push push) {

        HttpEntity<Push> request = new HttpEntity<>(push);

        CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
        CompletableFuture.allOf(pushNotification).join();

        FirebaseResponse firebaseResponse = null;

        try {
            firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return firebaseResponse;
    }

    /**
     * Envia notificação para API do firebase
     * <p>
     * Método utiliza CompletableFuture com @Async para realizar
     * chamada assicrona na API do Firebase
     *
     * @param entity
     * @return
     */
    @Async
    private CompletableFuture<FirebaseResponse> send(HttpEntity<Push> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + fcmKey));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        FirebaseResponse firebaseResponse = restTemplate.postForObject(FCM_API, entity, FirebaseResponse.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
