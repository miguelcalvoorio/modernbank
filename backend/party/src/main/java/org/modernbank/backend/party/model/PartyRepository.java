package org.modernbank.backend.party.model;

import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.modernbank.backend.party.client.PartySearchRequest;
import org.modernbank.backend.party.utilities.model.PageModel;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheQuery;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class PartyRepository implements ReactivePanacheMongoRepository<Party> {

    public Uni<PartyContainerModel> findAllParties(int page, int size, String sortBy) {
        // Create empty query expression
        Document queryDocument = new Document();

        // Create sort expression
        Document sortDocument = new Document();
        if (sortBy != null && sortBy.length() > 1) {
            if (sortBy.charAt(0) == '-') {
                // Remember to remove first char from 'sortBy' string
                sortDocument.append(sortBy.substring(1), -1);
            } else {
                // No need to remove first char from 'sortBy' string
                sortDocument.append(sortBy, 1);
            }
        }

        // Execute query
        ReactivePanacheQuery<Party> query = this.find(queryDocument, sortDocument)
            .page(new Page(page, size));
        
        // Create page
        Uni<PageModel> pageModel = Uni.combine().all().unis(query.count(), query.pageCount()).with(PageModel::new);

        return Uni.combine().all().unis(pageModel, query.list()).with(PartyContainerModel::new);
    }

    public Uni<Party> findPartyById(String uuid) {
        Document queryDocument = new Document();
        queryDocument.append("uuid", uuid);

        return this.find(queryDocument).singleResultOptional()
            .onItem().transform(party -> party.isPresent() ? party.get() : null);
    }

    public Uni<PartyContainerModel> searchParties(int page, int size, PartySearchRequest searchRequest, String sortBy) {
        
        // Create search query expression
        Document queryDocument = new Document();
        if (searchRequest != null) {
            // For strings, we will use MongoDB's regex with
            // case-insensitive options and stard & end anchors: /^ABC$/i
            if (searchRequest.getName() != null && searchRequest.getName() != "") queryDocument.append(
                "name",
                new BsonRegularExpression("^" + searchRequest.getName(), "i"));
            if (searchRequest.getLastName() != null && searchRequest.getLastName() != "") queryDocument.append(
                "lastName",
                new BsonRegularExpression("^" + searchRequest.getLastName(), "i"));
            if (searchRequest.getEmail() != null && searchRequest.getEmail() != "") queryDocument.append(
                "contacts.contact",
                new BsonRegularExpression("^" + searchRequest.getEmail() + "$", "i"));
            if (searchRequest.getDocumentId() != null && searchRequest.getDocumentId() != "") queryDocument.append(
                "identifications.documentId",
                new BsonRegularExpression("^" + searchRequest.getDocumentId() + "$", "i"));
            if (searchRequest.getStatus() != null) queryDocument.append(
                "status",
                searchRequest.getStatus().name()  
            );
            if (searchRequest.getNationality() != null) queryDocument.append(
                "nationality",
                searchRequest.getNationality().name()  
            );
            if (searchRequest.getDateOfBirth() != null) queryDocument.append(
                "dateOfBirth",
                searchRequest.getDateOfBirth()
            );
        }

        // Create sort expression
        Document sortDocument = new Document();
        if (sortBy != null && sortBy.length() > 1) {
            if (sortBy.charAt(0) == '-') {
                // Remember to remove first char from 'sortBy' string
                sortDocument.append(sortBy.substring(1), -1);
            } else {
                // No need to remove first char from 'sortBy' string
                sortDocument.append(sortBy, 1);
            }
        }

        // Execute query
        ReactivePanacheQuery<Party> query = this.find(queryDocument, sortDocument)
            .page(new Page(page, size));

        // Create page
        Uni<PageModel> pageModel = Uni.combine().all().unis(query.count(), query.pageCount()).with(PageModel::new);

        return Uni.combine().all().unis(pageModel, query.list()).with(PartyContainerModel::new);
    }
    
    public Uni<Party> persistParty(@Valid Party party) {
        return this.persist(party);
    }

    public Uni<Party> updateParty(@Valid Party party) {
        return this.update(party);
    }

    public Uni<Boolean> deleteParty(ObjectId id) {
        return this.deleteById(id);
    }
}
