// package com.example.shopping.repository;

// import com.example.shopping.model.NewOrder;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface NewOrderRepository extends JpaRepository<NewOrder, Integer> {
// }

package com.example.shopping.repository;

import com.example.shopping.model.NewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// FIX: Ensure the ID type is Long, matching your NewOrder entity's primary key
public interface NewOrderRepository extends JpaRepository<NewOrder, Long> {
    // You can add custom query methods here if needed
}
