package hard.fooddeliverysystem.service;

import hard.fooddeliverysystem.models.Address;
import hard.fooddeliverysystem.models.DeliveryPartner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class DeliveryAssignmentService {

    private final List<DeliveryPartner> partners = new ArrayList<>();
    private final AtomicInteger rrIndex = new AtomicInteger(0);

    public void registerPartner(DeliveryPartner partner) {
        if (partner != null) {
            partners.add(partner);
        }
    }

    public Optional<DeliveryPartner> assign(Address restaurant, Address customer) {
        if (partners.isEmpty()) return Optional.empty();
        // naive round-robin among available partners
        int attempts = 0;
        while (attempts < partners.size()) {
            int idx = Math.abs(rrIndex.getAndIncrement() % partners.size());
            DeliveryPartner p = partners.get(idx);
            if (p.isAvailable()) {
                p.setAvailable(false);
                return Optional.of(p);
            }
            attempts++;
        }
        return Optional.empty();
    }

    public void setAvailable(DeliveryPartner partner) {
        if (partner != null) partner.setAvailable(true);
    }
}
