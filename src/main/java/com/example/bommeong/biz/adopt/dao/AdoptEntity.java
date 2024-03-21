package com.example.bommeong.biz.adopt.dao;

import com.example.bommeong.biz.adopt.dto.AdoptModel;
import com.example.bommeong.biz.post.dao.PostEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "adopt")
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
public class AdoptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adopt_id")
    private Long adoptId;

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", unique = true, nullable = false)
    private PostEntity postEntity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "adoptEntity", cascade = CascadeType.ALL)
    private AdoptApplicationEntity AdoptApplicationEntity;

    public AdoptEntity(AdoptModel model) {
        this.adoptId = model.getAdoptId();
        PostEntity post = new PostEntity();
        post.setPostId(model.getPostId());
        this.postEntity = post;
        this.status = model.getStatus();
    }

    public AdoptModel toModel() { return new AdoptModel(this); }

}
