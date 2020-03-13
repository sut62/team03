package SE62.team03.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.*;
import java.util.stream.Collectors;

import SE62.team03.Repository.*;
import SE62.team03.Entity.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080") // ทำให้ spingboot , vue ส่งข้อมูหากันได้
class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ClubsRepository clubsRepository;

    @Autowired
    private ClubTypeRepository ClubTypeRepository;

    @Autowired
    private ClubmemberRepository clubmemberRepository;

    BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    

    @GetMapping("/budget")
    public Collection<Budget> Budget() {
        return budgetRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/budget/{amount}/{clubId}/{clubtypeId}/{memberId}")
    public Budget addBudget(
        @PathVariable long clubId, 
        @PathVariable long clubtypeId,
        @PathVariable long memberId,
        @PathVariable int amount
        ) {

        Budget budget = new Budget();
        Clubs clubs = clubsRepository.findById(clubId);
        ClubType clubtype = ClubTypeRepository.findById(clubtypeId);
        Clubmember member = clubmemberRepository.findById(memberId);


        budget.setClubs(clubs);
        budget.setClubType(clubtype);
        budget.setClubmember(member);
        budget.setAmount(amount);
        return budgetRepository.save(budget);

    }
}
