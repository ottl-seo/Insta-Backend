package efub.insta.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column
    private String content;

    @Column
    private Long fileSize;

    @Column
    private String originalFileName;

    @Column
    private String filePath;
}
