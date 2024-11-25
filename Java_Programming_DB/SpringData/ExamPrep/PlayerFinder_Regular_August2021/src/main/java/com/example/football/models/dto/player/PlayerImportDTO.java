package com.example.football.models.dto.player;

import com.example.football.models.dto.stat.StatXMLImportDTO;
import com.example.football.models.dto.team.TeamXMLImportDTO;
import com.example.football.models.dto.town.TownXMLImportDTO;

import com.example.football.util.xmlLocalDateAdapter.XmlLocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDTO {

    @Size(min = 2)
    @NotNull
    @XmlElement(name = "first-name")
    private String firstName;

    @Size(min = 2)
    @NotNull
    @XmlElement(name = "last-name")
    private String lastName;

    @Email
    @NotNull
    @XmlElement
    private String email;

    @NotNull
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate birthDate;

    @NotNull
    @XmlElement
    private String position;

    @NotNull
    @XmlElement
    private TownXMLImportDTO town;

    @NotNull
    @XmlElement
    private TeamXMLImportDTO team;

    @NotNull
    @XmlElement
    private StatXMLImportDTO stat;
}
