import jpql.Member;
import jpql.MemberDto;
import jpql.Team;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

public class JpaMain {



        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

            EntityManager em = emf.createEntityManager();/*JPA에서는 항상 트랜잭션 관리를 해줘야*/

            EntityTransaction tr = em.getTransaction();
            tr.begin();

            try {



                    Team team1 = new Team();
                    team1.setName("team1");
                    em.persist(team1);

                    Team team2 = new Team();
                    team2.setName("team2");
                    em.persist(team2);

                    Member member1 = new Member();
                    member1.setUserName("member1");
                    member1.setAge(10);
                    member1.changeTeam(team1);
                    em.persist(member1);

                Member member2 = new Member();
                member2.setUserName("member2");
                member2.setAge(10);
                member2.changeTeam(team1);
                em.persist(member2);

                Member member3 = new Member();
                member3.setUserName("member3");
                member3.setAge(10);
                member3.changeTeam(team2);
                em.persist(member3);


                    em.flush();
                    em.clear();

                    String query = "select m from Member m where m.team = :team1";
//                    String query = "select m.userName FROM Member m";



                    int resultcount = em.createQuery("update Member m set m.age = 20")
                            .executeUpdate()
                            ;


                tr.commit();

            } catch (Exception e) {
                tr.rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
            emf.close();
        }
}
