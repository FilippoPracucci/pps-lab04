package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import u03.extensionmethods.Optionals.*
import u03.extensionmethods.Sequences.*
import Sequence.*

class SchoolModelTest:

  val schoolModel: SchoolModule = BasicSchoolModule
  import schoolModel.*

  val school = emptySchool

  val john = teacher("John")
  val math = course("Math")
  val physics = course("Physics")

  @Test def testCourses(): Unit =
    val school = schoolModel.emptySchool
    assertEquals(Nil(), school.courses())

  @Test def testTeachers(): Unit =
    val school = schoolModel.emptySchool
    assertEquals(Nil(), school.teachers())

  @Test def testSetTeacherToCourse(): Unit =
    val school2 = school.setTeacherToCourse(john, math)
    assertEquals(cons(john, Nil()), school2.teachers())
    assertEquals(cons(math, Nil()), school2.courses())

  @Test def testCoursesOfATeacher(): Unit =
    val school2 = school.setTeacherToCourse(john, math).setTeacherToCourse(john, physics)
    assertEquals(cons(math, cons(physics, Nil())), school2.coursesOfATeacher(john))

  @Test def testHasTeacher(): Unit =
    val school2 = school.setTeacherToCourse(john, math)
    assertTrue(school2.hasTeacher("John"))
    assertFalse(school2.hasTeacher("Mary"))

  @Test def testHasCourse(): Unit =
    val school2 = school.setTeacherToCourse(john, math)
    assertTrue(school2.hasCourse("Math"))
    assertFalse(school2.hasCourse("History"))
