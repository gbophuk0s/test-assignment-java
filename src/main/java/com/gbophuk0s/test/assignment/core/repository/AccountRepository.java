package com.gbophuk0s.test.assignment.core.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gbophuk0s.test.assignment.core.model.Account;

public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> findAllByClientId(Connection connection, String id) throws SQLException;

}
