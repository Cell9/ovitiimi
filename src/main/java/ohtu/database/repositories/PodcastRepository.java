/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ohtu.database.entities.data.Podcast;

/**
 *
 * @author Roni
 */
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

}
