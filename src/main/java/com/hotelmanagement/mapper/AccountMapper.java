package com.hotelmanagement.mapper;

import com.hotelmanagement.dto.request.Account.AccountCreationRequest;
import com.hotelmanagement.dto.request.User.UserUpdateRequest;
import com.hotelmanagement.dto.response.Account.AccountResponse;
import com.hotelmanagement.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Accounts toAccount(AccountCreationRequest request);
    AccountResponse toAccountResponse(Accounts accounts);
    void updateAccount(@MappingTarget Accounts accounts , UserUpdateRequest request);
}
