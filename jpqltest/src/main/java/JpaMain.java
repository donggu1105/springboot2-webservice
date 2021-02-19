import jpql.Member;
import jpql.MemberDto;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JpaMain {



        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

            EntityManager em = emf.createEntityManager();/*JPA에서는 항상 트랜잭션 관리를 해줘야*/

            EntityTransaction tr = em.getTransaction();
            tr.begin();

            try {

                for(int i = 0 ; i < 100; i++) {

                    Team team = new Team();
                    team.setName("team"+i);
                    em.persist(team);

                    Member member = new Member();
                    member.setUserName("userName");
                    member.setAge(10);
                    member.changeTeam(team);
                    em.persist(member);
                }

                    em.flush();
                    em.clear();

                    String query = "select m from Member m left join Team t on m.userName = t.name";
                    List<Member> result = em.createQuery(query, Member.class)

                            .getResultList();

                    for(Member m : result) {
                        System.out.println(m);
                    }

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
