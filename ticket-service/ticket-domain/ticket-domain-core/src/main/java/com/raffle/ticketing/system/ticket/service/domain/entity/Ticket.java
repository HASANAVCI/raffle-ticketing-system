package com.raffle.ticketing.system.ticket.service.domain.entity;

import com.raffle.ticketing.system.domain.entity.AggregateRoot;
import com.raffle.ticketing.system.domain.valueobject.*;
import com.raffle.ticketing.system.ticket.service.domain.exception.TicketDomainException;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TicketItemId;
import com.raffle.ticketing.system.ticket.service.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

public class Ticket extends AggregateRoot<TicketId> {
    private final CustomerId customerId;
    private final OfficeId officeId;
    private final Money price;
    private final List<TicketItem> items;

    /*
     Non-final trackingId, ticketStatus and failureMessages. Because they can change when processing business logic..
     */
    private TrackingId trackingId;
    private TicketStatus ticketStatus;
    private List<String> failureMessages;

    public void initializeTicketing() {
        setId(new TicketId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        ticketStatus = TicketStatus.PENDING;
        initializeTicketItems();
    }

    public void validateTicket() {
        validateInitializeTicket();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void pay() {
        if (ticketStatus != TicketStatus.PENDING) {
            throw new TicketDomainException("Ticket is not in correct state for pay operation!");
        }
        ticketStatus = TicketStatus.PAID;
    }

    public void approve() {
        if (ticketStatus != TicketStatus.PAID) {
            throw new TicketDomainException("Ticket is not in correct state for approve operation!");
        }
        ticketStatus = TicketStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (ticketStatus != TicketStatus.PAID) {
            throw new TicketDomainException("Ticket is not in correct state for initCancel operation!");
        }
        ticketStatus = TicketStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    public void cancel(List<String> failureMessages) {
        if (!(ticketStatus == TicketStatus.CANCELLING || ticketStatus == TicketStatus.PENDING)) {
            throw new TicketDomainException("Ticket is not in correct state for cancel operation!");
        }
        ticketStatus = TicketStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void validateItemsPrice() {
        Money ticketItemsTotal = items.stream().map(ticketItem -> {
            validateItemPrice(ticketItem);
            return ticketItem.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!price.equals(ticketItemsTotal)) {
            throw new TicketDomainException("Total price: " + price.getAmount()
                    + " is not equal to Ticket items total: " + ticketItemsTotal.getAmount() + "!");
        }
    }

    private void validateItemPrice(TicketItem ticketItem) {
        if (!ticketItem.isPriceValid()) {
            throw new TicketDomainException("Ticket item price: " + ticketItem.getPrice().getAmount()
                    + "is not valid for ticket " + ticketItem.getRaffle().getId().getValue());
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new TicketDomainException("Total price must be greater than zero!");
        }
    }

    private void validateInitializeTicket() {
        if (ticketStatus != null || getId() != null) {
            throw new TicketDomainException("Ticket is not in correct state for initialization!");
        }
    }

    private void initializeTicketItems() {
        long itemId = 1;
        for (TicketItem ticketItem: items) {
            ticketItem.initializeTicketItem(super.getId(), new TicketItemId(itemId++));
        }
    }

    private Ticket(Builder builder) {
        super.setId(builder.ticketId);
        customerId = builder.customerId;
        officeId = builder.officeId;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        ticketStatus = builder.ticketStatus;
        failureMessages = builder.failureMessages;
    }


    public CustomerId getCustomerId() {
        return customerId;
    }

    public OfficeId getOfficeId() {
        return officeId;
    }

    public Money getPrice() {
        return price;
    }

    public List<TicketItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private TicketId ticketId;
        private CustomerId customerId;
        private OfficeId officeId;
        private Money price;
        private List<TicketItem> items;
        private TrackingId trackingId;
        private TicketStatus ticketStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder ticketId(TicketId val) {
            ticketId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder officeId(OfficeId val) {
            officeId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<TicketItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder ticketStatus(TicketStatus val) {
            ticketStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
